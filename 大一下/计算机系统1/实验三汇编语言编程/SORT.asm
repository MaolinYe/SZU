	.ORIG X3000


	LD R0,DATA
	LD R1,COPY
	LD R2,COUNTER
LOOP1	BRZ NEXT1
	LDR R3,R0,#0
	ADD R0,R0,#1
	STR R3,R1,#0
	ADD R1,R1,#1
	ADD R2,R2,#-1
	BRNZP LOOP1


NEXT1	LD R1,BUBBLE
LOOP2	BRZ NEXT2
	LD R0,COPY
	LD R2,BUBBLE
LOOP3	BRZ AGAIN
	LDR R3,R0,#0
	ADD R0,R0,#1
	LDR R4,R0,#0
	NOT R5,R3
	ADD R5,R5,#1
	ADD R5,R5,R4
	BRNZ RIGHT
	STR R3,R0,#0
	ADD R0,R0,#-1
	STR R4,R0,#0
	ADD R0,R0,#1
RIGHT	ADD R2,R2,#-1
	BRNZP LOOP3
AGAIN	ADD R1,R1,#-1
	BRNZP LOOP2


NEXT2	LD R0,COPY
	AND R1,R1,#0
	AND R2,R2,#0
	LD R3,COUNTER
LOOP4	BRZ FINISH
	LDR R4,R0,#0
	LD R6,A
	LD R7,B
	NOT R4,R4
	ADD R4,R4,#1
	ADD R6,R6,R4
	BRP BB
	NOT R5,R0
	ADD R5,R5,#1
	LD R6,AHOLD
	ADD R6,R6,R5
	BRN BB
	ADD R1,R1,#1
	BRNZP TAIL
BB	ADD R7,R7,R4
	BRP TAIL
	LD R7,BHOLD
	ADD R7,R7,R5
	BRN TAIL
	ADD R2,R2,#1
TAIL	ADD R0,R0,#1
	ADD R3,R3,#-1
	BRNZP LOOP4


FINISH	STI R1,ANUM
	STI R2,BNUM


	HALT


DATA	.FILL X3200
COPY	.FILL X4000
COUNTER	.FILL X0010
ANUM	.FILL X4100
BNUM 	.FILL X4101
AHOLD	.FILL X4003
BHOLD	.FILL X4007
A	.FILL #85
B	.FILL #75
BUBBLE	.FILL #15


	.END