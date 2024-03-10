		.orig x3000

		and r0,r0,#0
		lea r6,ASCIIBUFF
		ld r5,char

again		ld r4,count
multiply	add r0,r0,r0
		add r4,r4,#-1
		brp multiply
		ldr r2,r6,#0
		add r6,r6,#1
		add r2,r2,r5
		add r0,r0,r2
		add r1,r1,#-1
		brp again
		halt

ASCIIBUFF	.blkw 10
char		.fill x-30
count		.fill #10
		.end