ALTER TABLE t_role ADD CONSTRAINT role_user FOREIGN KEY(f_id) REFERENCES t_user (f_id);
ALTER TABLE t_role_permission ADD CONSTRAINT role_permission_role FOREIGN KEY(f_role_id) REFERENCES t_role (f_id);
ALTER TABLE t_permission ADD CONSTRAINT permission_role_permission FOREIGN KEY(f_id) REFERENCES t_role_permission (f_role_id)