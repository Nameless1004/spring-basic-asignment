CREATE TABLE `schedules`
(
    `schedule_id`  bigint       NOT NULL COMMENT '스케쥴아이디',
    `manager_id`   bigint       NOT NULL COMMENT '매니저코드',
    `password`     varchar(20)  NOT NULL COMMENT '비밀번호',
    `contents`     varchar(200) NOT NULL COMMENT '할일',
    `created_time` varchar(100)  NULL COMMENT '작성일',
    `updated_time` varchar(100)  NULL COMMENT '수정일'
);

CREATE TABLE `managers`
(
    `manager_id`   bigint       NOT NULL COMMENT '매니저코드',
    `name`         varchar(40)  NOT NULL COMMENT '이름',
    `email`        varchar(40)  NULL COMMENT '이메일',
    `created_time` varchar(100)  NULL COMMENT '등록일',
    `updated_time` varchar(100)  NULL COMMENT '수정일'
);

ALTER TABLE `schedules`
    ADD CONSTRAINT `PK_SCHEDULES` PRIMARY KEY (
                                               `schedule_id`
        );

ALTER TABLE `managers`
    ADD CONSTRAINT `PK_MANAGERS` PRIMARY KEY (
                                              `manager_id`
        );

ALTER TABLE managers
    MODIFY manager_id bigint not null auto_increment;
ALTER TABLE schedules
    MODIFY schedule_id bigint not null auto_increment;

ALTER TABLE schedules
    add constraint schedule_fk_manager_id foreign key (manager_id) references managers (manager_id) on delete cascade

