/*
 Navicat Premium Data Transfer

 Source Server         : mySql
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : site_screen_params

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 18/07/2020 23:04:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for data_screen_setting
-- ----------------------------
DROP TABLE IF EXISTS `data_screen_setting`;
CREATE TABLE `data_screen_setting`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `loop_interval` int(255) NULL DEFAULT NULL,
  `data_id_one` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `data_id_two` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `data_id_three` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `data_id_four` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `data_id_five` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `data_id_six` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `data_id_seven` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `data_id_eight` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loop_data` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of data_screen_setting
-- ----------------------------
INSERT INTO `data_screen_setting` VALUES (1, 6000, '人员信息', '环境信息', '车辆信息', '塔吊信息', '签到信息', '车辆信息', '塔吊信息', '巡检信息', 1);

-- ----------------------------
-- Table structure for main_screen_setting
-- ----------------------------
DROP TABLE IF EXISTS `main_screen_setting`;
CREATE TABLE `main_screen_setting`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `selected_screen` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loop_interval` int(255) NULL DEFAULT NULL,
  `main_screen` int(255) NULL DEFAULT NULL,
  `loop_screen` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of main_screen_setting
-- ----------------------------
INSERT INTO `main_screen_setting` VALUES (1, '1;3;5;7', 3000, 0, 0);

-- ----------------------------
-- Table structure for map_screen_setting
-- ----------------------------
DROP TABLE IF EXISTS `map_screen_setting`;
CREATE TABLE `map_screen_setting`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `main_map_type` int(255) NULL DEFAULT NULL,
  `map_loop_interval` int(255) NULL DEFAULT NULL,
  `data_loop_interval` int(255) NULL DEFAULT NULL,
  `data_type_one` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `data_type_two` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `all_display_data` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `all_display_maps` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loop_map` int(255) NULL DEFAULT NULL,
  `loop_data` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of map_screen_setting
-- ----------------------------
INSERT INTO `map_screen_setting` VALUES (1, 1, 4000, 4000, '人员信息', '车辆信息', 'tmpNA', 'tmpNA', 0, 0);

-- ----------------------------
-- Table structure for monitor_screen_setting
-- ----------------------------
DROP TABLE IF EXISTS `monitor_screen_setting`;
CREATE TABLE `monitor_screen_setting`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `display_type` int(255) NULL DEFAULT NULL,
  `loop_interval` int(255) NULL DEFAULT NULL,
  `monitor_id_one` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `monitor_id_two` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `monitor_id_three` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `monitor_id_four` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `monitor_id_five` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `monitor_id_six` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `monitor_id_seven` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `monitor_id_eight` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loop_data` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of monitor_screen_setting
-- ----------------------------
INSERT INTO `monitor_screen_setting` VALUES (1, 0, 70000, '#1', '#2', '#3', '#4', '#5', '#6', '#7', '#8', 2);

-- ----------------------------
-- Table structure for multi_files
-- ----------------------------
DROP TABLE IF EXISTS `multi_files`;
CREATE TABLE `multi_files`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `suffix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url_link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 261 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of multi_files
-- ----------------------------
INSERT INTO `multi_files` VALUES (256, 'E:/uploadFiles/', '.mp4', 'Mon Jul 06 15:03:10 CST 2020', 'http://127.0.0.1:5051/external/f7e9d0d0b5e2450c8339262b7115c2ec.m3u8', '党建家', 'VIDEO');
INSERT INTO `multi_files` VALUES (257, 'E:/uploadFiles/', '.mp4', 'Mon Jul 06 15:03:11 CST 2020', 'http://127.0.0.1:5051/external/2a3121bf53854f48bc689a43ba8df066.m3u8', '党建家', 'VIDEO');
INSERT INTO `multi_files` VALUES (258, 'E:/uploadFiles/', '.mp4', 'Mon Jul 06 15:03:12 CST 2020', 'http://127.0.0.1:5051/external/bb494303b13148cea2e4095ad45880ad.m3u8', '党建家', 'VIDEO');
INSERT INTO `multi_files` VALUES (259, 'E:/uploadFiles/', '.png', 'Tue Jul 07 17:26:13 CST 2020', 'http://127.0.0.1:5051/external/a11029b3ccca41e284a2cecf5042afa9.png', '党建家', 'IMAGE');
INSERT INTO `multi_files` VALUES (260, 'E:/uploadFiles/', '.jpg', 'Tue Jul 07 17:26:13 CST 2020', 'http://127.0.0.1:5051/external/84b7d6739c0441078ba6b243259e3d13.jpg', '党建家', 'IMAGE');
INSERT INTO `multi_files` VALUES (261, 'E:/uploadFiles/', '.jpg', 'Tue Jul 07 19:10:55 CST 2020', 'http://127.0.0.1:5051/external/4654e1fb33c54070becda932e4c714cd.jpg', '欢迎界面', 'IMAGE');

-- ----------------------------
-- Table structure for multi_screen_setting
-- ----------------------------
DROP TABLE IF EXISTS `multi_screen_setting`;
CREATE TABLE `multi_screen_setting`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `display_type` int(255) NULL DEFAULT NULL,
  `multi_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loop_interval` int(255) NULL DEFAULT NULL,
  `trans_style` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of multi_screen_setting
-- ----------------------------
INSERT INTO `multi_screen_setting` VALUES (1, 0, 'Not Available', 6000, '显示');

-- ----------------------------
-- Table structure for prog_screen_setting
-- ----------------------------
DROP TABLE IF EXISTS `prog_screen_setting`;
CREATE TABLE `prog_screen_setting`  (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `screen_no` int(50) NULL DEFAULT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `set_time` datetime(0) NULL DEFAULT NULL,
  `set_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of prog_screen_setting
-- ----------------------------
INSERT INTO `prog_screen_setting` VALUES (8, 2, '2020-01-14 16:10:56', '2020-01-14 18:11:01', '2020-01-14 17:11:05', '5675');
INSERT INTO `prog_screen_setting` VALUES (10, 6, '2020-01-14 19:23:31', '2020-01-14 21:23:35', '2020-01-14 19:23:39', '5675');
INSERT INTO `prog_screen_setting` VALUES (11, 1, '2020-02-18 11:37:31', '2020-02-18 12:37:33', '2020-02-18 11:37:37', '5675');

-- ----------------------------
-- Table structure for screen_info
-- ----------------------------
DROP TABLE IF EXISTS `screen_info`;
CREATE TABLE `screen_info`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `screen_num` int(255) NULL DEFAULT NULL,
  `screen_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `screen_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content_type` int(255) NULL DEFAULT NULL,
  `create_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `width` double(255, 0) NULL DEFAULT NULL,
  `length` double(255, 0) NULL DEFAULT NULL,
  `resolution_x` int(255) NULL DEFAULT NULL,
  `resolution_y` int(255) NULL DEFAULT NULL,
  `state` int(255) NULL DEFAULT NULL,
  `memo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `data_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of screen_info
-- ----------------------------
INSERT INTO `screen_info` VALUES (1, 1, '欢迎词界面', '欢迎词和背景显示', 1, '2019-08-08', '2019-08-08', '5675', 1, 1, 1280, 720, 1, '无备注信息...', '无默认数据信息...');
INSERT INTO `screen_info` VALUES (2, 2, '文本信息界面', '显示多页文本信息', 2, '2019-08-08', '2019-08-08', '5675', 1, 1, 1280, 720, 1, '无备注信息...', '无默认数据信息...');
INSERT INTO `screen_info` VALUES (3, 3, '多媒体界面', '播放照片或者视频', 3, '2019-08-08', '2019-08-08', '5675', 1, 1, 1280, 720, 1, '无备注信息...', '无默认数据信息...');
INSERT INTO `screen_info` VALUES (4, 4, '监控显示界面', '展示监控画面', 4, '2019-08-08', '2019-08-08', '5675', 1, 1, 1280, 720, 1, '无备注信息...', '无默认数据信息...');
INSERT INTO `screen_info` VALUES (5, 5, '工地一张图界面', '工地一张图以及部分数据展示', 5, '2019-08-08', '2019-08-08', '5675', 1, 1, 1280, 720, 1, '无备注信息...', '无默认数据信息...');
INSERT INTO `screen_info` VALUES (6, 6, '数据显示界面', '数据展示界面', 6, '2019-08-08', '2019-08-08', '5675', 1, 1, 1280, 720, 1, '无备注信息...', '无默认数据信息...');

-- ----------------------------
-- Table structure for selection_dict
-- ----------------------------
DROP TABLE IF EXISTS `selection_dict`;
CREATE TABLE `selection_dict`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `colors` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `data_types` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `monitor_ids` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bg_types` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fonts` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `font_size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `trans_styles` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ui_types` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `map_types` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of selection_dict
-- ----------------------------
INSERT INTO `selection_dict` VALUES (1, '黑色;白色;蓝色;红色', '人员信息;环境信息;车辆信息;塔吊信息;签到信息;巡检信息', '#1;#2;#3;#4;#5;#6;#7;#8;#9;#10;#11;#12', '纯色;图片;视频', '宋体;黑体;微软雅黑;楷体;仿宋;隶书;幼圆;华文行楷;华文彩云', '18;24;30;36;48;60;72;84;96;108;120', '淡出;切入;显示;擦除;闪烁', '5分屏;8分屏', '线划图;正射影像;3D模型;全景视频');

-- ----------------------------
-- Table structure for text_content
-- ----------------------------
DROP TABLE IF EXISTS `text_content`;
CREATE TABLE `text_content`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `txt_set_num` int(255) NULL DEFAULT NULL,
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `txt_pos_x` int(255) NULL DEFAULT NULL,
  `txt_pos_y` int(255) NULL DEFAULT NULL,
  `txt_font` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `txt_size` int(255) NULL DEFAULT NULL,
  `txt_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title_txt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of text_content
-- ----------------------------
INSERT INTO `text_content` VALUES (1, 1, '一、安全检查制度：\n安全检查是消除事故隐患，预防事故，保证安全生产的重要手段和措施。为了不断改善生产条件和作业环境，使作业环境达到最佳状态。从而采取有效对策，消除不安全因素，保障安全生产，特制定安全检查制度如下：\n1.安全检查的内容：按照建筑部颁发的《建筑施工现场安全检查评分标准》，对照检查执行情况；基槽临边的防护；施工用电、施工机具安全设施，操作行为，劳动防护用品的正确使用和安全防火等。\n2.安全检查的方法：定期检查、突击性检查、专业性检查、季节性和节假日前后的检查和经常性检查。\n', 0, 0, '宋体', 24, 'rgba(30, 144, 255, 1)', '安全信息告知');
INSERT INTO `text_content` VALUES (2, 1, '二、安全技术交底制度：\n严格进行安全技术交底，认真执行安全技术措施，是贯彻安全生产方针，减少因工伤事故，实现安全生产的重要保证。\n1.工程开工前，由施工负责人和技术负责人组织有关人员根据工程特点、所处地理环境和施工方法制定细的安全技术措施，报上级有关技术、安全部门批准。批准的安全技术措施具有技术法规的作用，必须认真贯彻执行。\n2.工程开工时，由总工程师和技术负责人向组织施工的项目经理、施工员、安全员、班组长进行详细的安全技术交底，使执行者了解其道理，为安全技术措施的落实打下基础。\n3.每个单项工程开工前，工地项目经理要组织施工员向实际操作的班组成员将施工方法和安全技术措施作详细讲解，并以书面形式下达班组。\n4.施工员根据单项工程安全技术措施的安全设施、设备及安全注意事项的实施填写《安全技术交底表》责任人落实到班组、个人、履行签字验收制度。\n5.施工现场的生产组织者，不得对安全技术措施方案私自变更，如由合理的建议，应书面报总工程师批准，未批之前，仍按原方案贯彻执行。\n6.安全职能部门要以施工安全技术措施为依据，以安全法规和各项安全规章制度为准则，经常性的对工地实施情况进行检查，并监督各项安全技术措施的落实。', 0, 0, '宋体', 24, 'rgba(30, 144, 255, 1)', '安全信息告知');
INSERT INTO `text_content` VALUES (3, 1, '3.每个单项工程开工前，工地项目经理要组织施工员向实际操作的班组成员将施工方法和安全技术措施作详细讲解，并以书面形式下达班组。\n4.施工员根据单项工程安全技术措施的安全设施、设备及安全注意事项的实施填写《安全技术交底表》责任人落实到班组、个人、履行签字验收制度。\n5.施工现场的生产组织者，不得对安全技术措施方案私自变更，如由合理的建议，应书面报总工程师批准，未批之前，仍按原方案贯彻执行。\n6.安全职能部门要以施工安全技术措施为依据，以安全法规和各项安全规章制度为准则，经常性的对工地实施情况进行检查，并监督各项安全技术措施的落实。', 0, 0, '宋体', 24, 'rgba(30, 144, 255, 1)', '安全信息告知');

-- ----------------------------
-- Table structure for text_screen_setting
-- ----------------------------
DROP TABLE IF EXISTS `text_screen_setting`;
CREATE TABLE `text_screen_setting`  (
  `id` int(10) NOT NULL,
  `total_num` int(255) NULL DEFAULT NULL,
  `loop_interval` int(255) NULL DEFAULT NULL,
  `trans_style` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loop_data` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of text_screen_setting
-- ----------------------------
INSERT INTO `text_screen_setting` VALUES (1, 3, 7000, '显示', 0);

-- ----------------------------
-- Table structure for welcome_screen_setting
-- ----------------------------
DROP TABLE IF EXISTS `welcome_screen_setting`;
CREATE TABLE `welcome_screen_setting`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `welcome_text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bg_mode` int(255) NULL DEFAULT NULL,
  `bg_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pic_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `txt_pos_x` int(255) NULL DEFAULT NULL,
  `txt_pos_y` int(255) NULL DEFAULT NULL,
  `txt_font` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `txt_size` int(255) NULL DEFAULT NULL,
  `txt_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `line_height` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of welcome_screen_setting
-- ----------------------------
INSERT INTO `welcome_screen_setting` VALUES (1, '欢迎姚老师姚老师莅临\n姚老师爆了！哈哈哈哈\n测试测试测试！\n', 1, 'rgba(250, 212, 0, 1)', 'http://127.0.0.1:5051/external/4654e1fb33c54070becda932e4c714cd.jpg', 0, 0, '幼圆', 96, 'rgba(30, 144, 255, 1)', '1');

SET FOREIGN_KEY_CHECKS = 1;
