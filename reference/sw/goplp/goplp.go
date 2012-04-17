/* 
 * a go implementation of a plp cpu simulator
 *
 * usage:
 *
 * todo: 
 *	disassemble the program into one nice big structure
 *		instruction type
 *		opcode
 *		registers
 *		immediate field
 *		jump field
 *		shift amount
 *	start simulating!
 *		pc
 *		register file
 *		branches/jumps get a delay slot
 *	output
 *		>> command line
 *			help
 *			step / s
 *			print / p
 *			watch / w
 *	
 */

package main

import (
	"fmt"
	"os"
	"flag"
)

const version = "1.0"

var (
	help		= flag.Bool("h", false, "print this help")
	plpfile		= flag.String("plp", "", "plp file to open")
	debug		= flag.Bool("v", false, "print extra information")
)

func log(arg ...interface{}) {
	if *debug {
		fmt.Println(arg)
	}
}

func usage() {
	fmt.Println("usage: goplp <options>")
	flag.PrintDefaults()
	os.Exit(1)
}

func init() {
	flag.Usage = usage
	flag.Parse()

	if *help {
		usage()
	}

	fmt.Println("goplp", version)
	fmt.Println("goplp  Copyright (C) 2012  David Fritz")
	fmt.Println("This program comes with ABSOLUTELY NO WARRANTY;")
	fmt.Println("This is free software, and you are welcome to redistribute it")
	fmt.Println("under certain conditions;")
}

func main() {
	if *plpfile != "" {
		newPLPFile(*plpfile)
	}
	var c Console
	r := make(chan []string)
	c.Init(os.Stdin, r)
	for {
		go c.Run()
		process(<-r)
	}
}
