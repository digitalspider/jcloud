alter table blog drop foreign key fk_blog_source_id;
drop index ix_blog_source_id on blog;

alter table cart drop foreign key fk_cart_user_id;
drop index ix_cart_user_id on cart;

alter table comment drop foreign key fk_comment_user_id;
drop index ix_comment_user_id on comment;

alter table invoice drop foreign key fk_invoice_user_id;
drop index ix_invoice_user_id on invoice;

alter table invoiceline drop foreign key fk_invoiceline_invoice_id;
drop index ix_invoiceline_invoice_id on invoiceline;

alter table purchase drop foreign key fk_purchase_user_id;
drop index ix_purchase_user_id on purchase;

alter table rating drop foreign key fk_rating_user_id;
drop index ix_rating_user_id on rating;

alter table request drop foreign key fk_request_user_id;
drop index ix_request_user_id on request;

alter table server drop foreign key fk_server_os_id;
drop index ix_server_os_id on server;

alter table server drop foreign key fk_server_user_id;
drop index ix_server_user_id on server;

alter table userrole drop foreign key fk_userrole_user;
drop index ix_userrole_user on userrole;

alter table userrole drop foreign key fk_userrole_role;
drop index ix_userrole_role on userrole;

drop table if exists blog;

drop table if exists blogsource;

drop table if exists cart;

drop table if exists category;

drop table if exists clickcount;

drop table if exists clickcountsummary;

drop table if exists comment;

drop table if exists invoice;

drop table if exists invoiceline;

drop table if exists link;

drop table if exists os;

drop table if exists page;

drop table if exists product;

drop table if exists purchase;

drop table if exists rating;

drop table if exists request;

drop table if exists role;

drop table if exists server;

drop table if exists service;

drop table if exists user;

drop table if exists userrole;

