	.orig x3000
	ld r0,zchar
	ld r1,counter
loop	trap x21
	add r1,r1,#-1
	brp loop
halt
zchar .fill #90
counter .fill #100
.end