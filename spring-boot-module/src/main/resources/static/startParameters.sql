#ITEM
INSERT INTO t_item( f_name, f_status, f_deleted) VALUES ('Test Item 1','ready',0);
INSERT INTO t_item( f_name, f_status, f_deleted) VALUES ('Test Item 2','ready',0);
#ROLE
INSERT INTO t_role( f_id, f_name) VALUES (1,'administrator');
INSERT INTO t_role( f_id, f_name) VALUES (2,'sale_user');
INSERT INTO t_role( f_id, f_name) VALUES (3,'customer_user');
#PERMISSION
INSERT INTO t_permission( f_id, f_name) VALUES (1,'ADMINISTRATOR');
INSERT INTO t_permission( f_id, f_name) VALUES (2,'SALE_USER');
INSERT INTO t_permission( f_id, f_name) VALUES (3,'CUSTOMER_USER');
#ROLE_PERMISSION
INSERT INTO t_role_permission(f_role_id, f_permission_id) VALUES (1, 1);
INSERT INTO t_role_permission(f_role_id, f_permission_id) VALUES (2, 2);
INSERT INTO t_role_permission(f_role_id, f_permission_id) VALUES (3, 3);
#USER
INSERT INTO t_user( f_id, f_name,f_password,f_role_id) VALUES (1,'Jack','$2a$10$vNUxTyG9P3uhBwS6PtQHo.ELruLTb5Z0WicPdnINlD.cs8dc9Ar3e',1);
INSERT INTO t_user( f_id, f_name,f_password,f_role_id) VALUES (2,'Mike','$2a$10$uMkeRHVMrBd6Dh8GFzPIme68irmLM7j6n6pgjgGmyjLMGrvOI12da',2);
INSERT INTO t_user( f_id, f_name,f_password,f_role_id) VALUES (3,'Tony','$2a$10$63VR/Vb2Exp0SkIESYF3LubvGRlPObpNs/neajf.fZ01pgIy24vCm',3)