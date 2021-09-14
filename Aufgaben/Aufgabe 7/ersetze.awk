BEGIN {
	printf("\nErstes Wort:");
	getline e < "-";
	printf("\nZweites Wort:");
	getline z < "-";
}


{gsub(e,z); print} $TARGETED_FILE
