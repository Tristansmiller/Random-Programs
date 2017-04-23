OBJS = babelfish sumj sumc sump summ fibj fibc fibp fibm

all: $(OBJS)

babelfish: Babelfish.java
		javac Babelfish.java
		echo "Generating files with Babelfish.java"
		java Babelfish
sumj: sum.java
	javac sum.java
sumc: sum.c
	gcc -o sum sum.c
sump: sum.py
	chmod +x sum.py
summ: sum.m

fibj: fib.java
	javac fib.java
fibc: fib.c
	gcc -o fib fib.c
fibp: fib.py
	chmod +x fib.py
fibm: fib.m

clean:
	$(RM) *.class sum.java sum.c sum.py sum.m fib.java fib.c fib.py fib.m fib sum $(OBJS)

