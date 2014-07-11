#! /usr/bin/env Rscript
# To identify the type of the script, here it is RScript

# Defining the variables with their initial values
city.key <- NA
page.value <- 0.0
# To initiating the connection to standard input
input <- file("stdin", open="r")

# Running while loop until all the lines are read
while (length(currentLine <- readLines(input, n=1)) > 0) {
  # Splitting the Mapper output line into vectors by
  # tab("\t") separator
  fields <- strsplit(currentLine, "\t")
  # capturing key and value form the fields
  # collecting the first data element from line which is city
  key <- fields[[1]][1]
  # collecting the pagepath value from line
  value <- as.character(fields[[1]][2])
  # setting up key and values
  # if block will check whether key attribute is
  # initialized or not. If not initialized then it will be # assigned from
  #collected key attribute with value from # mapper output. This is designed
  #to run at initial time.
  if (is.na(city.key)) {
    city.key <- key
    page.value <- value
  }
  else {
    # Once key attributes are set, then will match with the previous key
    #attribute value. If both of them matched then they will combined in to
    #one.
    if (city.key == key) {
      page.value <- c(page.value, value)
    }
    else {
      # if key attributes are set already but attribute value # is other than
      #previous one then it will emit the store #p agepath values along with
      #associated key attribute value of city,
      page.value <- unique(page.value)
      # printing key and value to standard output
      print(list(city.key, page.value),stdout())
      city.key <- key
      page.value <- value
    }
  }
}
print(list(city.key, page.value), stdout())
# closing the connection
close(input)
