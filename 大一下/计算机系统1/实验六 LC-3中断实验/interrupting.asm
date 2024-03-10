	.orig x2000

	add r6,r6,#-1
	str r0,r6,#0
	add r6,r6,#-1
	str r1,r6,#0
	add r6,r6,#-1
	str r2,r2,#0

return	ldi r0,kbsr
	brzp return
	ldi r0,kbdr
	ld r1,lf
	add r1,r1,r0
	brz tail
	ld r1,count
show	ldi r2,dsr
	brzp show
	sti r0,ddr
	add r1,r1,#-1
	brp show
	br return
	
tail	ldr r2,r6,#0
	add r6,r6,#1
	ldr r1,r6,#0
	add r6,r6,#1
	ldr r0,r6,#0
	add r6,r6,#1
	rti

kbsr	.fill xfe00
kbdr	.fill xfe02
dsr	.fill xfe04
ddr	.fill xfe06
count	.fill #10
lf	.fill x-0a
	.end