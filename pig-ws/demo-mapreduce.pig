/* demo.pig */
-- pig -x mapreduce demo-mapreduce.pig 

A = load 'input/passwd' using PigStorage(':');  -- load the passwd file 
B = foreach A generate $0 as id;  -- extract the user IDs 
store B into 'pig.out.demo-mapreduce';  -- write the results to a file name id.out

