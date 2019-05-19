/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50168
Source Host           : localhost:3306
Source Database       : ynnz_face_exam

Target Server Type    : MYSQL
Target Server Version : 50168
File Encoding         : 65001

Date: 2019-05-18 14:06:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_exam_add_student`
-- ----------------------------
DROP TABLE IF EXISTS `t_exam_add_student`;
CREATE TABLE `t_exam_add_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `college` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `exam_info_id` int(11) DEFAULT NULL,
  `gender` int(11) NOT NULL,
  `id_card` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `person_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `stu_number` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `classes_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `image_url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_exam_add_student
-- ----------------------------
INSERT INTO `t_exam_add_student` VALUES ('57', '4', '44', '2', '123123123123', '唐僧', '2018001', '计算机应用技术1班', null);
INSERT INTO `t_exam_add_student` VALUES ('58', '2', '44', '2', '123123123123', '孙悟空', '2018002', '计算机网络3班', null);
INSERT INTO `t_exam_add_student` VALUES ('59', '2', '44', '2', '123123123123', '猪八戒', '2018003', '电子商务3班', null);
INSERT INTO `t_exam_add_student` VALUES ('60', '1', '44', '1', '123123123123', '太白金星', '2018004', '软件一班', null);
INSERT INTO `t_exam_add_student` VALUES ('61', '1', '45', '1', '123123123123', '太白金星', '2018004', '软件一班', null);
INSERT INTO `t_exam_add_student` VALUES ('62', '4', '47', '2', '123123123123', '唐僧', '2018001', '计算机应用技术1班', null);
INSERT INTO `t_exam_add_student` VALUES ('63', '2', '46', '2', '123123123123', '猪八戒', '2018003', '电子商务3班', null);
INSERT INTO `t_exam_add_student` VALUES ('64', '1', '46', '1', '123123123123', '太白金星', '2018004', '软件一班', null);

-- ----------------------------
-- Table structure for `t_exam_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_exam_info`;
CREATE TABLE `t_exam_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `exam_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '试卷名称',
  `one_teacher_id` int(11) DEFAULT NULL COMMENT '监考教师一',
  `two_teacher_id` int(11) DEFAULT NULL COMMENT '监考教师一',
  `three_teacher_id` int(11) DEFAULT NULL COMMENT '监考教师一',
  `exam_room_id` int(11) DEFAULT NULL COMMENT '考场id',
  `start_date` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '开始时间',
  `end_date` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '结束时间',
  `exam_status` int(11) DEFAULT '1' COMMENT '考试状态 1.未开始  2.进行中   0.已结束',
  `exam_teacher` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `exam_room_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_exam_info
-- ----------------------------
INSERT INTO `t_exam_info` VALUES ('44', '计算机应用基础', '8', '9', '10', '1', '2019-05-12 00:00', '2019-05-13 00:00', '1', '张三,李四,王五', '绍年院一机房');
INSERT INTO `t_exam_info` VALUES ('45', 'HTML5 网页设计', '8', '0', '0', '2', '2019-05-12 08:00', '2019-05-13 00:00', '1', '张三', '德三楼302室');
INSERT INTO `t_exam_info` VALUES ('46', 'Photoshop UI设计', '0', '0', '10', '3', '2019-05-12 00:00', '2019-05-13 10:00', '1', '王五', '文兴楼201室');
INSERT INTO `t_exam_info` VALUES ('47', 'Photoshop UI设计', '9', '0', '10', '1', '2019-05-12 08:00', '2019-05-13 12:00', '1', '李四,王五', '绍年院一机房');

-- ----------------------------
-- Table structure for `t_exam_person`
-- ----------------------------
DROP TABLE IF EXISTS `t_exam_person`;
CREATE TABLE `t_exam_person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `college` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `gender` int(255) DEFAULT NULL,
  `id_card` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `person_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `stu_number` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `classes_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_exam_person
-- ----------------------------
INSERT INTO `t_exam_person` VALUES ('6', '4', '2', '123123123123', '唐僧', '2018001', '计算机应用技术1班');
INSERT INTO `t_exam_person` VALUES ('7', '2', '2', '123123123123', '孙悟空', '2018002', '计算机网络3班');
INSERT INTO `t_exam_person` VALUES ('8', '2', '2', '123123123123', '猪八戒', '2018003', '电子商务3班');
INSERT INTO `t_exam_person` VALUES ('9', '1', '1', '123123123123', '太白金星', '2018004', '软件一班');

-- ----------------------------
-- Table structure for `t_exam_room`
-- ----------------------------
DROP TABLE IF EXISTS `t_exam_room`;
CREATE TABLE `t_exam_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `exam_address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `person_num` int(11) DEFAULT NULL,
  `room_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_exam_room
-- ----------------------------
INSERT INTO `t_exam_room` VALUES ('1', '绍年院一机房', '40', '2');
INSERT INTO `t_exam_room` VALUES ('2', '德三楼302室', '39', '1');
INSERT INTO `t_exam_room` VALUES ('3', '文兴楼201室', '35', '1');

-- ----------------------------
-- Table structure for `t_teacher_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher_info`;
CREATE TABLE `t_teacher_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gender` int(11) DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `teacher_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `age` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `college` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `job_number` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_teacher_info
-- ----------------------------
INSERT INTO `t_teacher_info` VALUES ('8', '2', '12332112321', '张三', '40', '4', '001');
INSERT INTO `t_teacher_info` VALUES ('9', '2', '12332112321', '李四', '40', '2', '002');
INSERT INTO `t_teacher_info` VALUES ('10', '1', '12332112321', '王五', '40', '1', '003');

-- ----------------------------
-- Table structure for `t_user_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_type` int(2) DEFAULT '1',
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_user_info
-- ----------------------------
INSERT INTO `t_user_info` VALUES ('3', '123123', '1', 'admin');
INSERT INTO `t_user_info` VALUES ('5', '123456', '2', 'ynny');
INSERT INTO `t_user_info` VALUES ('6', '123456', '1', 'xiaoming');
INSERT INTO `t_user_info` VALUES ('7', '123456', '1', '374329927@qq.com');
INSERT INTO `t_user_info` VALUES ('9', '123456', '2', '孙悟空');
