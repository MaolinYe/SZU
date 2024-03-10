	.orig x3000
	ldi r0,dividend
	ldi r1,divisor
	and r2,r2,#0
	not r1,r1
	add r1,r1,#1
loop	add r0,r0,r1
	brn ace
	add r2,r2,#1
	sti r0,remainder
	sti r2,quotient
	brnzp loop
ace	halt
dividend .fill x4000
divisor	.fill x4001
quotient .fill x5000
remainder .fill x5001
	.end