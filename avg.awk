$1>0 {sum += $1; anz++}
END {printf ("AVG = %f\n", sum/anz); }
