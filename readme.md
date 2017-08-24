# Hive part 2
### program and script were tested on local VM and aws.
The main problem was with using of uber jar, so do not use it in udf.
Uber jar was the reason of some tez exception on aws: org.apache.hadoop.fs.FileSystem.listLocatedStatus not found.
Maybe(?) this exception does not throwing if you use hive on mapreduce.
