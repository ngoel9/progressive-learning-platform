#include <stdio.h>
#include <stdlib.h>
#include "parser.tab.h"
#include "log.h"

/* for getopts */
#include <ctype.h>
#include <unistd.h>

int LOG_LEVEL = 0;

static char *S_FILE_INPUT = NULL;
static char *S_FILE_OUTPUT = NULL;
static FILE *FILE_INPUT = NULL;
static FILE *FILE_OUTPUT = NULL;

void handle_opts(int argc, char *argv[]) {
	char *dvalue = NULL;
	char *ivalue = NULL;
	char *ovalue = NULL;

	int c;

	opterr = 0;
	
	while ((c = getopt(argc, argv, "d:i:o:")) != -1)
		switch (c) {
			case 'd':
				dvalue = optarg;
				break;
			case 'i':
				ivalue = optarg;
				break;
			case 'o':
				ovalue = optarg;
				break;
			case '?':
				if (optopt == 'd' ||
				    optopt == 'i' ||
				    optopt == 'o' )
					printf("[e] option -%c requires an argument.\n", optopt);
				else if (isprint (optopt))
					printf("[e] unknown option '-%c'.\n", optopt);
				else
					printf("[e] unknown option character '\\x%x'.\n", optopt);
					exit(1);
				default:
					exit(1);
		}

	if (dvalue != NULL) {
		LOG_LEVEL = atoi(dvalue);
		vlog("[plpc] setting log level\n");
	}

	if (ivalue != NULL) {
		S_FILE_INPUT = ivalue;
		log("[plpc] input file: ");
		log(S_FILE_INPUT);
		log("\n");
	} else {
		err("[plpc] no input file specified, use -i <input file>\n");
		exit(1);
	}

	if (ovalue != NULL) {
		S_FILE_OUTPUT = ovalue;
		log("[plpc] output file: ");
		log(S_FILE_OUTPUT);
		log("\n");
	} else {
		S_FILE_OUTPUT = malloc(9);
		sprintf(S_FILE_OUTPUT,"plpc.out");
		log("[plpc] output file: ");
		log(S_FILE_OUTPUT);
		log("\n");
	}
}

int main(int argc, char *argv[]) {

	/* process arguments */
	handle_opts(argc, argv);	

	/* open files */
	vlog("[plpc] opening files\n");
	FILE_INPUT = fopen(S_FILE_INPUT,"r");
	if (FILE_INPUT == NULL) {
		err("[plpc] cannot open input file!\n");
		exit(1);
	}
	FILE_OUTPUT = fopen(S_FILE_OUTPUT,"w");
	if (FILE_OUTPUT == NULL) {
		err("[plpc] cannot open output file!\n");
		exit(1);
	}
	yyset_in(FILE_INPUT);

	log("[plpc] starting parser\n");
	yyparse();

	vlog("[plpc] closing files\n");
	fclose(FILE_INPUT);
	fclose(FILE_OUTPUT);

	log("[plpc] done\n");
	return 0;
}