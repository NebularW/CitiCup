import csv

if __name__ == "__main__":
	filename = "sheet.csv"
	csv_file_reader = csv.reader(open(filename, 'r', encoding="utf-8"))

	d = []
	for line in csv_file_reader:
		try:
			age = int(line[0])
		except ValueError:
			continue
		else:
			d.append(float(line[6]))

	print(d)
			# print("d[%d] = %f" % (age, d[age]))
