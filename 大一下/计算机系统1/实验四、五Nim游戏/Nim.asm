	.orig x3000

again	jsr print
	jsr datain1
	jsr print
	jsr datain2
	br again


print	st r0,save_r0
	st r1,save_r1
	st r7,save_r7
	lea r0,row_a
	puts
	ld r0,stone
	ld r1,num_a
loop_a	out
	add r1,r1,#-1
	brp loop_a
	ld r0,cr
	out
	lea r0,row_b
	puts
	ld r0,stone
	ld r1,num_b
loop_b	out
	add r1,r1,#-1
	brp loop_b
	ld r0,cr
	out
	lea r0,row_c
	puts
	ld r0,stone
	ld r1,num_c
loop_c	out
	add r1,r1,#-1
	brp loop_c
	ld r0,cr
	out
	ld r0,save_r0
	ld r1,save_r1
	ld r7,save_r7
	ret

save_r0 .fill #0
save_r1 .fill #0
stone 	.fill x006f
cr	.fill x000d
row_a	.stringz "ROW A: "
row_b	.stringz "ROW B: "
row_c	.stringz "ROW C: "
num_a	.fill #3
num_b	.fill #5
num_c	.fill #8


cue1	st r0,save_r0
	st r7,save_r7
	lea r0,play1
	puts
	ld r0,save_r0
	ld r7,save_r7
	ret
play1	.stringz "Player 1,choose a row and number of rocks:"


cue2	st r0,save_r0
	st r7,save_r7
	lea r0,play2
	puts
	ld r0,save_r0
	ld r7,save_r7
	ret
play2	.stringz "Player 2,choose a row and number of rocks:"
save_r7 .fill #0


datain1	st r0,save_r0
	st r2,save_r2
	st r3,save_r3
	st r7,saver7
try1	jsr cue1
	getc
	out
	add r2,r0,#0
	not r2,r2
	add r2,r2,#1
	getc
	out
	add r3,r0,#0
	ld r0,lf
	out
test1a	ld r0,char_a
	add r0,r2,r0
	brnp test1b
	ld r0,char_0
	not r0,r0
	add r0,r0,#1
	add r0,r0,r3
	brn error1
	ld r3,num_a
	not r0,r0
	add r0,r0,#1
	add r3,r0,r3
	brn error1
	st r3,num_a
	ld r3,sum_abc
	add r3,r3,r0
	brz win1
	st r3,sum_abc
	br save
test1b	ld r0,char_b
	add r0,r2,r0
	brnp test1c
	ld r0,char_0
	not r0,r0
	add r0,r0,#1
	add r0,r0,r3
	brn error1
	ld r3,num_b
	not r0,r0
	add r0,r0,#1
	add r3,r0,r3
	brn error1
	st r3,num_b
	ld r3,sum_abc
	add r3,r3,r0
	brz win1
	st r3,sum_abc
	br save
test1c	ld r0,char_c
	add r0,r2,r0
	brnp error1
	ld r0,char_0
	not r0,r0
	add r0,r0,#1
	add r0,r0,r3
	brn error1
	ld r3,num_c
	not r0,r0
	add r0,r0,#1
	add r3,r0,r3
	brn error1
	st r3,num_c
	ld r3,sum_abc
	add r3,r3,r0
	brz win1
	st r3,sum_abc
	br save		
win1	ld r0,lf
	out
	lea r0,wins1
	puts	
	halt
error1	lea r0,invalid
	puts
	ld r0,lf
	out
	br try1

datain2	st r0,save_r0
	st r2,save_r2
	st r3,save_r3
	st r7,saver7
try2	jsr cue2
	getc
	out
	add r2,r0,#0
	not r2,r2
	add r2,r2,#1
	getc
	out
	add r3,r0,#0
	ld r0,lf
	out
test2a	ld r0,char_a
	add r0,r2,r0
	brnp test2b
	ld r0,char_0
	not r0,r0
	add r0,r0,#1
	add r0,r0,r3
	brn error2
	ld r3,num_a
	not r0,r0
	add r0,r0,#1
	add r3,r0,r3
	brn error2
	st r3,num_a
	ld r3,sum_abc
	add r3,r3,r0
	brz win2
	st r3,sum_abc
	br save
test2b	ld r0,char_b
	add r0,r2,r0
	brnp test2c
	ld r0,char_0
	not r0,r0
	add r0,r0,#1
	add r0,r0,r3
	brn error2
	ld r3,num_b
	not r0,r0
	add r0,r0,#1
	add r3,r0,r3
	brn error2
	st r3,num_b
	ld r3,sum_abc
	add r3,r3,r0
	brz win2
	st r3,sum_abc
	br save
test2c	ld r0,char_c
	add r0,r2,r0
	brnp error2
	ld r0,char_0
	not r0,r0
	add r0,r0,#1
	add r0,r0,r3
	brn error2
	ld r3,num_c
	not r0,r0
	add r0,r0,#1
	add r3,r0,r3
	brn error2
	st r3,num_c
	ld r3,sum_abc
	add r3,r3,r0
	brz win2
	st r3,sum_abc
	br save	
	
win2	ld r0,lf
	out
	lea r0,wins2
	puts	
	halt
error2	lea r0,invalid
	puts
	ld r0,lf
	out
	br try2
save	ld r0,lf
	out	
	ld r0,saver0
	ld r2,save_r2
	ld r3,save_r3
	ld r7,saver7
	ret

lf	.fill x000a
char_a	.fill x0041
char_b	.fill x0042
char_c	.fill x0043
char_0	.fill x0030
wins1	.stringz "Player 1 Wins."
wins2	.stringz "Player 2 Wins."
invalid	.stringz "Invalid move. Try again."
sum_abc	.fill #16
saver0	.fill #0
save_r2 .fill #0
save_r3 .fill #0
saver7	.fill #0
	.end