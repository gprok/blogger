INSERT INTO users (name, email, password, role) VALUES ('admin', 'aa@blogger.local', '1111', 'ROLE_ADMIN')
INSERT INTO users (name, email, password, role) VALUES ('johndoe', 'jd@blogger.local', '1111', 'ROLE_BLOGGER')
INSERT INTO users (name, email, password, role) VALUES ('pattismith', 'ps@blogger.local', '1111', 'ROLE_BLOGGER')
INSERT INTO users (name, email, password, role) VALUES ('raycharles', 'rc@blogger.local', '1111', 'ROLE_BLOGGER')

INSERT INTO blogs (title, description, user_id) VALUES ('Spring Boot tutorials', 'A place to learn Spring Boot', 2)
INSERT INTO blogs (title, description, user_id) VALUES ('All about Java', 'Java secrets discussed here', 2)
INSERT INTO blogs (title, description, user_id) VALUES ('Photography', 'Photos for travelling', 3)

INSERT INTO posts (title, content, blog_id) VALUES ('Spring Boot 101', 'In this tutorial we are ... ', 1)
INSERT INTO posts (title, content, blog_id) VALUES ('Hibernate 101', 'We discuss Hibernate here ... ', 1)
INSERT INTO posts (title, content, blog_id) VALUES ('Inheritance', 'This post explains inheritance ... ', 2)
