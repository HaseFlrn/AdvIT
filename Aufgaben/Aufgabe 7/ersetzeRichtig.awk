BEGIN {
	printf("FROM = ");
	getline FROM < "-"
	printf("TO =");
	getline TO < "-"
}
{
	for(j=1; j<NF; j++)
		if ($j == FROM)

			printf("%s ", TO);
		else
			printf("%s ", $j);
	printf("\n");
}
