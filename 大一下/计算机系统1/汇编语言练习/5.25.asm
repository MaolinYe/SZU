.orig x3000
not r1,r3
add r1,r1,#1
add r1,r2,r1
brz equal
brp bigger
add r1,r3,#0
halt
equal and r1,r1,#0
halt
bigger add r1,r2,#0
halt
.end