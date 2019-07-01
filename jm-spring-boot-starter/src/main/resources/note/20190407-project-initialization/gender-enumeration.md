# Gender Enumeration

Gender data from: https://abcnews.go.com/blogs/headlines/2014/02/heres-a-list-of-58-gender-options-for-facebook-users/

Here's a List of 58 Gender Options for Facebook Users. The following are the 58 gender options identified by ABC News:

'Agender', 'Androgyne', 'Androgynous', 'Bigender', 'Cis', 'Cisgender', 'Cis Female', 'Cis Male', 'Cis Man', 'Cis Woman', 'Cisgender Female', 'Cisgender Male', 'Cisgender Man', 'Cisgender Woman', 'Female to Male', 'FTM', 'Gender Fluid', 'Gender Nonconforming', 'Gender Questioning', 'Gender Variant', 'Genderqueer', 'Intersex', 'Male to Female', 'MTF', 'Neither', 'Neutrois', 'Non-binary', 'Other', 'Pangender', 'Trans', 'Trans*', 'Trans Female', 'Trans* Female', 'Trans Male', 'Trans* Male', 'Trans Man', 'Trans* Man', 'Trans Person', 'Trans* Person', 'Trans Woman', 'Trans* Woman', 'Transfeminine', 'Transgender', 'Transgender Female', 'Transgender Male', 'Transgender Man', 'Transgender Person', 'Transgender Woman', 'Transmasculine', 'Transsexual', 'Transsexual Female', 'Transsexual Male', 'Transsexual Man', 'Transsexual Person', 'Transsexual Woman', 'Two-Spirit'

And here is 26 gender options for `jm-spring-boot-starter`:

```
'Agender','Androgyne','Bigender','Cisgender','Cisgender Female','Cisgender Male','Female to Male','Gender Fluid','Gender Nonconforming','Gender Questioning','Gender Variant','Genderqueer','Intersex','Male to Female','Neither','Neutrois','Non-binary','Other','Pangender','Transfeminine','Transgender','Transgender Female','Transgender Male','Transgender Person','Transmasculine','Two-Spirit'
```

MySQL create table statement (from `jm_spring_boot_template.sql`)

```mysql
CREATE TABLE `t_user` (
  `id` bigint(64) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Primary key of user',
  `username` varchar(50) NOT NULL COMMENT 'Username',
  `email` varchar(100) NOT NULL COMMENT 'Email',
  `cellphone` varchar(11) DEFAULT NULL COMMENT 'Cellphone number',
  `password` varchar(60) NOT NULL COMMENT 'Password',
  `full_name` varchar(255) DEFAULT NULL COMMENT 'Full name',
  `birthday` date DEFAULT NULL COMMENT 'Birthday',
  `gender` enum('Agender','Androgyne','Bigender','Cisgender','Cisgender Female','Cisgender Male','Female to Male','Gender Fluid','Gender Nonconforming','Gender Questioning','Gender Variant','Genderqueer','Intersex','Male to Female','Neither','Neutrois','Non-binary','Other','Pangender','Transfeminine','Transgender','Transgender Female','Transgender Male','Transgender Person','Transmasculine','Two-Spirit') DEFAULT NULL COMMENT '26 gender options',
  `gmt_created` datetime NOT NULL COMMENT 'Created time',
  `gmt_modified` datetime NOT NULL COMMENT 'Modified time',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT 'Status. Enabled-1，Disenabled-0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `cellphone_UNIQUE` (`cellphone`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='User Table';
```


