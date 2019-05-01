# Alter column name and comment.
ALTER TABLE t_role
    CHANGE update_time modify_time datetime NULL COMMENT 'Modify time';
ALTER TABLE t_user
    CHANGE update_time modify_time datetime NULL COMMENT 'Modify time';

ALTER TABLE t_permission
    CHANGE name description varchar(50) NOT NULL COMMENT 'Permission description' AFTER url;
