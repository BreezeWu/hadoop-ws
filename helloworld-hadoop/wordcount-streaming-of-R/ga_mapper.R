#! /usr/bin/env Rscript
# To identify the type of the script, here it is RScript

# To disable the warning massages to be printed
options(warn=-1)

### (1)standalone: linux shell CMD;  
##    chmod u+x ga*.R
##    cat gadata_sample.csv | ga_mapper.R |sort | ga_reducer.R
### (2)Distrubuted, From a command prompt
###
### (3)Distrubuted, From a command prompt

## input file: gadata_mr.csv
## each line is an input to Mapper and the output City:PagePath. 
## City is a key and PagePath is a value. 

## Now Reducer can get all the page paths for a given city; 
## hence, it can be grouped easily.

# To initiating the connection to standard input
input <- file("stdin", "r")

# Each line has these four fields (date, country, city, and pagePath) 
# in the same order. We split the line by a comma.
# The result is a vector which has the date, country, city, and pathPath 
# in the indexes 1,2,3, and 4 respectively.

# Running while loop until all the lines are read
while(length(currentLine <- readLines(input, n=1, warn=FALSE)) > 0) {
  # Splitting the line into vectors by "," separator
  fields <- unlist(strsplit(currentLine, ","))
  # Capturing the city and pagePath from fields
  city <- as.character(fields[3])
  pagepath <- as.character(fields[4])
  # Printing both to the standard output
  print(paste(city, pagepath,sep="\t"),stdout())
}
# Closing the connection to that input stream
close(input)

