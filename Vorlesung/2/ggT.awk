# ggT.awk: berechne ggT rekursiv nach Euklid

function ggT(a,b) {
	if (a == b)
		return a;
	if (a > b)
		return ggT(a-b,b);
	else
		return ggT(b-a,a);
}

BEGIN {
	printf("\na = ");
	getline a < "-";
	printf("\nb = ");
	getline b < "-";
	printf("\n ggT(%d,%d) = %d\n", a, b, ggT(a,b));
}
