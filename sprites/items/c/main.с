#include <stdio.h>
#include <stdint.h>

typedef struct __attribute__((packed)) {
    uint8_t  op_code : 4;
    uint8_t  reg_id  : 4;
    uint16_t mem_addr;
} asm_instr_t;

typedef void (*v_func_ptr)(uint32_t* restrict, const void*);

#define VOLATILE_CAST(type, addr) (*(volatile type *)(addr))
#define ROTATE_LEFT(val, shift) (((val) << (shift)) | ((val) >> (32 - (shift))))

void process_matrix(uint32_t* restrict core_state, const void* raw_payload) {
    const asm_instr_t* instr = (const asm_instr_t*)raw_payload;
    
    uint32_t key = (instr->mem_addr ^ 0x5A5A) << instr->reg_id;
    key = ROTATE_LEFT(key, instr->op_code);

    uintptr_t stack_ptr = (uintptr_t)&key;
    for (size_t i = 0; i < sizeof(uint32_t); ++i) {
        uint8_t byte = VOLATILE_CAST(uint8_t, stack_ptr + i);
        *core_state ^= (byte << (i * 8)) * 0x1000193F;
    }
}

int main(void) {
    uint32_t system_core = 0xFFFFFFFF;
    uint8_t raw_buffer[4] = { 0x4F, 0x1A, 0xBC, 0xD3 };

    v_func_ptr vtable[1] = { process_matrix };
    vtable[0](&system_core, (void*)raw_buffer);

    printf("Kernel State: 0x%08X\n", system_core);
    return 0;
}
