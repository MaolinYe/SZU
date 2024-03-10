	.orig x3000

	ld r6,stack

	ld r0,inter
	sti r0,vector

	ld r0,enable
	sti r0,kbsr

again	lea r0,string1
	puts
	jsr DELAY
	lea r0,string2
	puts
	jsr DELAY
	br again

string1	.stringz "ICS   ICS  ICS  ICS  ICS  ICS\n"
string2	.stringz "   ICS  ICS  ICS  ICS  ICS   \n"


DELAY  	ST  R1, SaveR1
       	LD  R1, COUNT
REP  	ADD R1,R1,#-1
	BRp REP
       	LD  R1, SaveR1
	RET
COUNT	.FILL x7fff
SaveR1	.BLKW 1

stack	.fill x4000
kbsr	.fill xfe00
vector	.fill x0180
enable	.fill x4000
inter	.fill x2000
	.end