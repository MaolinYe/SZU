	.orig x3000
	ld r4,last
	ld r3,string
	ld r1,enter
	not r1,r1
	add r1,r1,#1
loop	getc
	add r2,r0,r1
	brz ace
	str r0,r3,#0
	add r3,r3,#1
	brnzp loop
ace	str r4,r3,#0
	ld r0,string
	puts
	halt
last	.fill x0000
enter	.fill x0a
string	.fill x3333
	.end