global _start

section .data
    payload: db 0x4F, 0x1A, 0xBC, 0xD3
    core:    dd 0xFFFFFFFF
    magic:   dd 0x1000193F

section .text
_start:
    mov rsi, payload
    movzx eax, word [rsi+2]
    xor eax, 0x5A5A
    
    movzx ecx, byte [rsi+1]
    shl eax, cl
    
    movzx ecx, byte [rsi]
    and ecx, 0x0F
    rol eax, cl
    
    mov [rsp-4], eax
    lea rdi, [rsp-4]
    mov rbx, [core]
    xor rdx, rdx

.loop:
    cmp rdx, 4
    jge .exit
    
    movzx r8d, byte [rdi+rdx]
    xor rbx, r8
    imul rbx, [magic]
    
    inc rdx
    jmp .loop

.exit:
    mov [core], rbx
    
    mov eax, 60
    xor edi, edi
    syscall
