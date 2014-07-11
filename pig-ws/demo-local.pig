/* demo.pig */
-- pig -x local demo-local.pig 

A = load '/etc/passwd' using PigStorage(':');  -- load the passwd file 
B = foreach A generate $0 as id;  -- extract the user IDs 
store B into 'pig.out.demo-local';  -- write the results to a file name id.out

