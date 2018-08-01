-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `roles` varchar(255) NOT NULL COMMENT '角色',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT '用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '0192023a7bbd73250516f069df18b500', 'admin,user', '2018-07-23 00:05:00', '2018-07-23 00:05:00');
INSERT INTO `user` VALUES ('2', 'ftpuser', '0192023a7bbd73250516f069df18b500', 'user', '2018-07-22 21:28:31', '2018-07-22 21:28:31');
INSERT INTO `user` VALUES ('3', 'user01', '0192023a7bbd73250516f069df18b500', 'user', '2018-07-22 21:28:31', '2018-07-22 21:28:31');
INSERT INTO `user` VALUES ('4', 'user02', '0192023a7bbd73250516f069df18b500', 'user', '2018-07-22 21:28:31', '2018-07-22 21:28:31');
INSERT INTO `user` VALUES ('5', 'user03', '0192023a7bbd73250516f069df18b500', 'user', '2018-07-22 21:28:31', '2018-07-22 21:28:31');
INSERT INTO `user` VALUES ('6', 'user04', '0192023a7bbd73250516f069df18b500', 'user', '2018-07-22 21:28:31', '2018-07-22 21:28:31');
