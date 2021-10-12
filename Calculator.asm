TITLE Calculator           

INCLUDE Irvine32.inc
.data 

;Please choose either of the following operations:', 13,10,0
szMenu          db  "Now what do you want to do with those 2 numbers?", 13, 10
                db 'Add     (A)',13,10
                db 'Subtract    (S)',13,10
                db 'Divide      (D)',13,10
                db 'Multiply    (M)',13,10
                db 'Exit        (E)',13,10
                db 'Selection: ',0 ;13,10,0

myMenu          db 'Welcome!', 13, 10
                db 'Enter Integer 1: ',0
myInteger2      db 'Enter Integer 2: ',0;13,10,0
szInvalidMenu   db 13, 10, "Invalid Menu Choice!", 13, 10, 0
myTotal         db 'Total: ',13,10,0

.data?
Input1          dd  ?
Input2          dd  ?
Total           dd  ?

.code
Start:
    call    Clrscr
    mov     edx, offset myMenu
    call    WriteString
     
FirstNum:
    call    ReadInt
    test    eax, eax
    jz      Start
    mov     Input1, eax
 
SecondNum:
    mov     edx, offset myInteger2
    call    WriteString
    call    ReadInt
    test    eax, eax
    jz      SecondNum
    mov     Input2, eax
 
Menu:  
    mov     edx, offset szMenu
    call    WriteString
    call    ReadChar
 
    .if al == "A" || al == "a"
         
        Call Crlf
        mov edx, offset myTotal
        call WriteString
        mov eax, Total
        mov eax, Input1
        add eax, Input2
        Call WriteInt
        Call Crlf
     
        ;jmp start
         
    .elseif al == "S" || al == "s"
     
        Call Crlf
        mov edx, offset myTotal
        call WriteString
        mov eax, Total
        mov eax, Input1
        sub eax, Input2
        Call WriteInt
        Call Crlf
     
        ;jmp start
         
    .elseif al == "D" || al == "d"
      
        Call Crlf
        mov edx, offset myTotal
        call WriteString
        mov eax, Total
        mov eax, Input1
        div eax, Input2
        Call WriteInt
        Call Crlf
     
        ;jmp start
         
    .elseif al == "M" || al == "m"
         
        Call Crlf
        mov edx, offset myTotal
        call WriteString
        mov eax, Total
        mov eax, Input1
        mul eax, Input2
        Call WriteInt
        Call Crlf
     
        ;jmp start
   
   .elseif al == "E" || al == "e"
        exit
     
    .else
        mov     edx, offset szInvalidMenu
        call    WriteString
        jmp     Menu
    .endif
 
     
    call    WaitMsg
    exit
end Start
 
 
 
    exit
main ENDP
 
END main
