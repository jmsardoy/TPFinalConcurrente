#!/usr/bin/python

import sys

if len(sys.argv) != 3:
	print("Uso: " + sys.argv[1] + " <Log marcado> <Marcados erroneos>")
	quit()

log = open(sys.argv[1], 'r')
marcado_erroneo = open(sys.argv[2], 'r')

encontre_uno = 0
for log_line in log:
	ll = log_line.split()
	marcado_erroneo.seek(0)
	for error_line in marcado_erroneo:
		el = error_line.split()
		for i in range(len(el)):
			if el[i] == "1":
				if ll[i] != "0":
					if encontre_uno == 0:
						encontre_uno = 1
					else:
						print("Combinacion erronea en linea:")
						print(log_line)
						print("Comparada con:")
						print(error_line)
						quit()
		encontre_uno = 0
	encontre_uno = 0
print("No encontre ningun marcado invalido.")

