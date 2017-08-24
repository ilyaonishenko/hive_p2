add jar s3://bucket1ilya/jars/hive_p2-assembly-0.2.jar;
create temporary function parseUA as 'com.example.UserAgentParser';
create external table logs(
  hash1 string,
  dat string,
  num int,
  hash2 string,
  ua string,
  ips string,
  n1 int,
  n2 int,
  n3 int,
  hash3 string,
  hash4 string,
  smth string,
  smth2 string,
  n5 int,
  n6 int,
  n7 int,
  n8 int,
  n9 int,
  hash5 string,
  n10 int,
  n11 int,
  hash6 string,
  n12 int,
  seq string
) partitioned by (dt string)
row format delimited
fields terminated by '\t'
location 's3://bucket1ilya/data/';
alter table logs add partition (dt = '20130606') location 's3://bucket1ilya/data/20130606/';
alter table logs add partition (dt = '20130607') location 's3://bucket1ilya/data/20130607/';
alter table logs add partition (dt = '20130608') location 's3://bucket1ilya/data/20130608/';
alter table logs add partition (dt = '20130609') location 's3://bucket1ilya/data/20130609/';
alter table logs add partition (dt = '20130610') location 's3://bucket1ilya/data/20130610/';
alter table logs add partition (dt = '20130611') location 's3://bucket1ilya/data/20130611/';
alter table logs add partition (dt = '20130612') location 's3://bucket1ilya/data/20130612/';
create table tmp_ua(col string);
insert overwrite table tmp_ua
    select parseUA(ua) from logs;
create table ua(browser string, os string, device string);
insert overwrite table ua
    select
        regexp_extract(col, '(.*)\t(.*)\t(.*)', 1) browser,
        regexp_extract(col, '(.*)\t(.*)\t(.*)', 2) os,
        regexp_extract(col, '(.*)\t(.*)\t(.*)', 3) device
            from tmp_ua;
create table top_browsers(browser string, freq int);
create table top_oss(os string, freq int);
create table top_devices(device string, freq int);
insert overwrite table top_browsers
    select
        browser browser,
        count(browser) freq
        from ua
        group by browser
        sort by freq desc;
insert overwrite table top_oss
    select
        os os,
        count(os) freq
        from ua
        group by os
        sort by freq desc;
insert overwrite table top_devices
    select
        device device,
        count(device) freq
        from ua
        group by device
        sort by freq desc;