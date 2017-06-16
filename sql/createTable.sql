CREATE TABLE FansInfo (
  fans_uk CHAR (20) PRIMARY KEY,
  is_craw CHAR (1),
  album_count INT,
  avatar_url VARCHAR (30),
  fans_count INT,
  fans_uname VARCHAR (20),
  follow_count INT,
  follow_time VARCHAR (20),
  intro VARCHAR (10),
  is_vip VARCHAR (2),
  pubshare_count INT,
  type VARCHAR (2),
  Suser_type VARCHAR (2)
)

CREATE TABLE FileInfo (
  category VARCHAR (20),
  fs_id VARCHAR (20),
  isdir CHAR (2),
  md5 VARCHAR (50),
  path VARCHAR (50),
  server_filename VARCHAR (20),
  sign VARCHAR (20),
  size VARCHAR (20),
  time_stamp VARCHAR (20)
)

  CREATE TABLE FollowInfo (
  follow_uk CHAR (20) PRIMARY KEY,
  is_claw CHAR (2),
  album_count INT,
  avatar_url VARCHAR (20),
  fans_count INT,
  follow_count INT,
  follow_time VARCHAR (20),
  follow_uname VARCHAR (20),
  intro VARCHAR (20),
  is_vip VARCHAR (20),
  pubshare_count INT,
  type VARCHAR (10),
  user_type VARCHAR (10)
)

CREATE TABLE ShareInfo (
  shareid CHAR (20) PRIMARY KEY,
  title VARCHAR (20),
  uk CHAR (20),
  username VARCHAR (20),
  shorturl VARCHAR (20),
  data_id VARCHAR (20),
  avatar_url VARCHAR (20),
  category VARCHAR (20),
  clienttype INT,
  dCnt INT,
  dir_cnt INT,
  feed_time VARCHAR (20),
  feed_type INT,
  filecount INT,
  source_id VARCHAR (20),
  source_uid VARCHAR (20),
  tCnt INT,
  third VARCHAR (20),
  vCnt INT

)