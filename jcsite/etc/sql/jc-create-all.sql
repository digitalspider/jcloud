create table blog (
  id                            bigint auto_increment not null,
  status                        integer,
  source_id                     bigint not null,
  link                          varchar(255) not null,
  title                         varchar(255) not null,
  author                        varchar(255),
  uri                           varchar(255),
  publisted_date                datetime(6) not null,
  description_type              varchar(255),
  description                   varchar(512),
  content_type                  varchar(255),
  content                       TEXT,
  tags                          varchar(255),
  cdate                         datetime(6) not null,
  mdate                         datetime(6) not null,
  constraint uq_blog_link unique (link),
  constraint pk_blog primary key (id)
);

create table blogsource (
  id                            bigint auto_increment not null,
  status                        integer,
  url                           varchar(255),
  link                          varchar(255) not null,
  title                         varchar(255) not null,
  description                   varchar(512),
  author                        varchar(255),
  type                          varchar(255),
  publisted_date                datetime(6) not null,
  tags                          varchar(255),
  entries                       integer,
  cdate                         datetime(6) not null,
  mdate                         datetime(6) not null,
  constraint uq_blogsource_link unique (link),
  constraint pk_blogsource primary key (id)
);

create table cart (
  id                            bigint auto_increment not null,
  status                        integer,
  user_id                       bigint not null,
  cdate                         datetime(6) not null,
  mdate                         datetime(6) not null,
  constraint pk_cart primary key (id)
);

create table category (
  id                            bigint auto_increment not null,
  status                        integer,
  name                          varchar(255),
  cdate                         datetime(6) not null,
  mdate                         datetime(6) not null,
  constraint pk_category primary key (id)
);

create table clickcount (
  id                            bigint auto_increment not null,
  status                        integer,
  domain                        varchar(255),
  url                           varchar(255),
  username                      varchar(255),
  user_agent                    varchar(255),
  ip_address                    varchar(255),
  cdate                         datetime(6) not null,
  mdate                         datetime(6) not null,
  constraint pk_clickcount primary key (id)
);

create table clickcountsummary (
  id                            bigint auto_increment not null,
  status                        integer,
  date                          datetime(6),
  url                           varchar(255),
  count                         varchar(255),
  cdate                         datetime(6) not null,
  mdate                         datetime(6) not null,
  constraint pk_clickcountsummary primary key (id)
);

create table comment (
  id                            bigint auto_increment not null,
  status                        integer,
  content                       varchar(255),
  user_id                       bigint not null,
  cdate                         datetime(6) not null,
  mdate                         datetime(6) not null,
  constraint pk_comment primary key (id)
);

create table invoice (
  id                            bigint auto_increment not null,
  status                        integer,
  user_id                       bigint not null,
  month                         integer,
  year                          integer,
  description                   varchar(255),
  price                         decimal(38),
  currency                      varchar(3),
  cdate                         datetime(6) not null,
  mdate                         datetime(6) not null,
  constraint pk_invoice primary key (id)
);

create table invoiceline (
  id                            bigint auto_increment not null,
  status                        integer,
  invoice_id                    bigint not null,
  description                   varchar(255),
  price                         decimal(38),
  currency                      varchar(3),
  cdate                         datetime(6) not null,
  mdate                         datetime(6) not null,
  constraint pk_invoiceline primary key (id)
);

create table link (
  id                            bigint auto_increment not null,
  status                        integer,
  name                          varchar(255),
  url                           varchar(255),
  click_count                   integer,
  cdate                         datetime(6) not null,
  mdate                         datetime(6) not null,
  constraint pk_link primary key (id)
);

create table os (
  id                            bigint auto_increment not null,
  status                        integer,
  distribution                  varchar(255),
  version                       varchar(255),
  architecture                  varchar(255),
  cdate                         datetime(6) not null,
  mdate                         datetime(6) not null,
  constraint pk_os primary key (id)
);

create table page (
  id                            bigint auto_increment not null,
  status                        integer,
  description                   varchar(255),
  tags                          varchar(255),
  type                          varchar(255),
  author                        varchar(255),
  title                         varchar(255),
  content                       varchar(255),
  url                           varchar(255),
  cdate                         datetime(6) not null,
  mdate                         datetime(6) not null,
  constraint pk_page primary key (id)
);

create table product (
  id                            bigint auto_increment not null,
  status                        integer,
  name                          varchar(255),
  cost_price                    decimal(38),
  list_price                    decimal(38),
  sale_price                    decimal(38),
  cdate                         datetime(6) not null,
  mdate                         datetime(6) not null,
  constraint pk_product primary key (id)
);

create table purchase (
  id                            bigint auto_increment not null,
  status                        integer,
  user_id                       bigint not null,
  date                          datetime(6),
  cdate                         datetime(6) not null,
  mdate                         datetime(6) not null,
  constraint pk_purchase primary key (id)
);

create table rating (
  id                            bigint auto_increment not null,
  status                        integer,
  rating                        integer,
  content                       varchar(255),
  user_id                       bigint not null,
  cdate                         datetime(6) not null,
  mdate                         datetime(6) not null,
  constraint pk_rating primary key (id)
);

create table request (
  id                            bigint auto_increment not null,
  status                        integer,
  title                         varchar(255),
  content                       varchar(255),
  type                          varchar(255),
  user_id                       bigint not null,
  cdate                         datetime(6) not null,
  mdate                         datetime(6) not null,
  constraint pk_request primary key (id)
);

create table role (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  constraint pk_role primary key (id)
);

create table server (
  id                            bigint auto_increment not null,
  status                        integer,
  name                          varchar(255),
  lxd_id                        varchar(255),
  ip                            varchar(255),
  alias                         varchar(255),
  os_id                         bigint not null,
  architecture                  varchar(255),
  os_version                    varchar(255),
  description                   varchar(255),
  pdate                         datetime(6),
  price                         double,
  cpu_limit                     double,
  cpu_current                   double,
  cpu_peak                      double,
  mem_limit                     double,
  mem_current                   double,
  mem_peak                      double,
  hdd_limit                     double,
  hdd_current                   double,
  hdd_peak                      double,
  ssh_key                       varchar(255),
  parent_server_id              bigint,
  user_id                       bigint not null,
  cdate                         datetime(6) not null,
  mdate                         datetime(6) not null,
  constraint pk_server primary key (id)
);

create table service (
  id                            bigint auto_increment not null,
  status                        integer,
  name                          varchar(255),
  version                       varchar(255),
  alias                         varchar(255),
  description                   varchar(255),
  tags                          varchar(255),
  cdate                         datetime(6) not null,
  mdate                         datetime(6) not null,
  constraint pk_service primary key (id)
);

create table user (
  id                            bigint auto_increment not null,
  status                        integer,
  username                      varchar(255),
  email                         varchar(255),
  password                      varchar(255),
  first_name                    varchar(255),
  last_name                     varchar(255),
  cdate                         datetime(6) not null,
  mdate                         datetime(6) not null,
  constraint pk_user primary key (id)
);

create table userrole (
  user_id                       bigint not null,
  role_id                       bigint not null,
  constraint pk_userrole primary key (user_id,role_id)
);

alter table blog add constraint fk_blog_source_id foreign key (source_id) references blogsource (id) on delete restrict on update restrict;
create index ix_blog_source_id on blog (source_id);

alter table cart add constraint fk_cart_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_cart_user_id on cart (user_id);

alter table comment add constraint fk_comment_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_comment_user_id on comment (user_id);

alter table invoice add constraint fk_invoice_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_invoice_user_id on invoice (user_id);

alter table invoiceline add constraint fk_invoiceline_invoice_id foreign key (invoice_id) references invoice (id) on delete restrict on update restrict;
create index ix_invoiceline_invoice_id on invoiceline (invoice_id);

alter table purchase add constraint fk_purchase_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_purchase_user_id on purchase (user_id);

alter table rating add constraint fk_rating_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_rating_user_id on rating (user_id);

alter table request add constraint fk_request_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_request_user_id on request (user_id);

alter table server add constraint fk_server_os_id foreign key (os_id) references os (id) on delete restrict on update restrict;
create index ix_server_os_id on server (os_id);

alter table server add constraint fk_server_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_server_user_id on server (user_id);

alter table userrole add constraint fk_userrole_user foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_userrole_user on userrole (user_id);

alter table userrole add constraint fk_userrole_role foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_userrole_role on userrole (role_id);

