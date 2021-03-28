-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2021-03-22 10:36:05
-- 服务器版本： 8.0.20
-- PHP 版本： 7.4.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `android`
--

-- --------------------------------------------------------

--
-- 表的结构 `discuss`
--

CREATE TABLE `discuss` (
  `id` int NOT NULL,
  `title` varchar(128) COLLATE utf8mb4_general_ci NOT NULL,
  `content` varchar(4096) COLLATE utf8mb4_general_ci NOT NULL,
  `author` int NOT NULL,
  `date` date NOT NULL,
  `note` varchar(1024) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 转存表中的数据 `discuss`
--

INSERT INTO `discuss` (`id`, `title`, `content`, `author`, `date`, `note`) VALUES
(1, 'test', 'test', 1, '2020-12-26', NULL),
(2, 'test', '1234123', 1, '2020-12-26', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `paper`
--

CREATE TABLE `paper` (
  `id` int NOT NULL,
  `start_date` datetime NOT NULL DEFAULT '1000-01-01 00:00:00',
  `note` varchar(1024) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `user_a` int DEFAULT NULL,
  `user_b` int DEFAULT '0',
  `user_a_score` int DEFAULT '-1',
  `user_b_score` int DEFAULT '-1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 表的结构 `plan`
--

CREATE TABLE `plan` (
  `id` int NOT NULL,
  `name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL,
  `content` varchar(4096) COLLATE utf8mb4_general_ci NOT NULL,
  `type` int NOT NULL,
  `note` varchar(1024) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 转存表中的数据 `plan`
--

INSERT INTO `plan` (`id`, `name`, `content`, `type`, `note`) VALUES
(1, '文言文', 'test', 0, 'PlanNote'),
(2, '九九表', 'test', 1, NULL),
(3, '单词', 'test', 2, NULL),
(4, '杂交', 'test', 3, NULL),
(5, '元素周期表', 'test', 4, NULL),
(6, '电路', 'test', 5, NULL),
(7, '马克思主义', 'test', 6, NULL),
(8, '海冰', 'test', 7, NULL),
(9, '秦始皇', 'test', 8, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `problem`
--

CREATE TABLE `problem` (
  `id` int NOT NULL,
  `type` int NOT NULL,
  `title` varchar(128) COLLATE utf8mb4_general_ci NOT NULL,
  `content` varchar(4096) COLLATE utf8mb4_general_ci NOT NULL,
  `ans` varchar(4096) COLLATE utf8mb4_general_ci NOT NULL,
  `note` varchar(1024) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 转存表中的数据 `problem`
--

INSERT INTO `problem` (`id`, `type`, `title`, `content`, `ans`, `note`) VALUES
(1, 1, '日本的首都是哪里？', '北京#东京#南京#天津', '东京', NULL),
(2, 1, '周树人的笔名', '周树人#冰心#杨绛#鲁迅', '鲁迅', NULL),
(3, 1, '第一个封建制王朝', '夏#商#周#秦汉', '夏', NULL),
(4, 1, '婉约派著名女诗人', '苏轼#辛弃疾#李清照#李白', '李清照', NULL),
(5, 1, '历史上第一位女皇帝', '武则天#汉景帝#乾隆#秦始皇', '武则天', NULL),
(6, 1, '王先生的QQ签名档最近改成了“庆祝弄璋之喜”，王先生近来的喜事是', '新婚#搬家#妻子生了个男孩#考试通过', '妻子生了个男孩', NULL),
(7, 1, '“爆竹声中一岁除，春风送暖入屠苏”，这里的“屠苏”指的是', '苏州#房屋#酒#庄稼', '酒', NULL),
(8, 1, '“月上柳梢头，人约黄昏后”描写的是哪个传统节日？', '中秋节#元宵节#端午节#七夕节', '元宵节', NULL),
(9, 1, '“一问三不知”出自《左传》，说的是哪“三不知”？', '天文、地理、文学#事情的开始、经过、结果#孔子、孟子、老子#自己的姓名、籍贯、生辰八字', '事情的开始、经过、结果', NULL),
(10, 1, '下列哪项不是端午节的习俗？', '挂香包#插艾蒿#登高采菊#喝雄黄酒', '登高采菊', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `reply`
--

CREATE TABLE `reply` (
  `id` int NOT NULL,
  `discuss_id` int NOT NULL,
  `author` int NOT NULL,
  `content` varchar(4096) COLLATE utf8mb4_general_ci NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 转存表中的数据 `reply`
--

INSERT INTO `reply` (`id`, `discuss_id`, `author`, `content`, `date`) VALUES
(5, 1, 1, '???', '2021-01-03'),
(7, 1, 1, 'test', '2021-01-05'),
(8, 1, 1, 'test', '2021-01-05'),
(9, 1, 1, 'test', '2021-01-05'),
(10, 1, 1, 'test', '2021-01-05'),
(11, 1, 1, 'test', '2021-01-05'),
(12, 1, 1, 'test', '2021-01-05'),
(14, 1, 1, 'test', '2021-01-05'),
(15, 1, 1, 'test', '2021-01-05');

-- --------------------------------------------------------

--
-- 表的结构 `roomproblem`
--

CREATE TABLE `roomproblem` (
  `id` int NOT NULL,
  `problem_id` int DEFAULT NULL,
  `room_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 转存表中的数据 `roomproblem`
--

INSERT INTO `roomproblem` (`id`, `problem_id`, `room_id`) VALUES
(321, 1, 72),
(322, 2, 72),
(323, 5, 72),
(324, 7, 72),
(325, 10, 72),
(326, 1, 73),
(327, 2, 73),
(328, 3, 73),
(329, 5, 73),
(330, 6, 73),
(331, 1, 74),
(332, 2, 74),
(333, 3, 74),
(334, 7, 74),
(335, 8, 74),
(336, 1, 75),
(337, 4, 75),
(338, 7, 75),
(339, 9, 75),
(340, 10, 75),
(341, 4, 76),
(342, 5, 76),
(343, 6, 76),
(344, 8, 76),
(345, 10, 76),
(346, 1, 77),
(347, 2, 77),
(348, 5, 77),
(349, 6, 77),
(350, 10, 77),
(351, 2, 78),
(352, 3, 78),
(353, 4, 78),
(354, 9, 78),
(355, 10, 78),
(356, 2, 79),
(357, 3, 79),
(358, 4, 79),
(359, 5, 79),
(360, 10, 79),
(361, 1, 80),
(362, 4, 80),
(363, 5, 80),
(364, 7, 80),
(365, 10, 80),
(366, 1, 81),
(367, 2, 81),
(368, 5, 81),
(369, 7, 81),
(370, 10, 81),
(371, 2, 82),
(372, 3, 82),
(373, 4, 82),
(374, 9, 82),
(375, 10, 82),
(376, 1, 83),
(377, 3, 83),
(378, 6, 83),
(379, 9, 83),
(380, 10, 83),
(381, 2, 84),
(382, 7, 84),
(383, 8, 84),
(384, 9, 84),
(385, 10, 84),
(386, 2, 85),
(387, 3, 85),
(388, 4, 85),
(389, 7, 85),
(390, 10, 85),
(391, 1, 86),
(392, 2, 86),
(393, 4, 86),
(394, 5, 86),
(395, 9, 86),
(396, 3, 87),
(397, 6, 87),
(398, 7, 87),
(399, 8, 87),
(400, 9, 87),
(401, 1, 88),
(402, 2, 88),
(403, 3, 88),
(404, 4, 88),
(405, 8, 88),
(406, 2, 89),
(407, 4, 89),
(408, 6, 89),
(409, 7, 89),
(410, 9, 89),
(411, 2, 90),
(412, 6, 90),
(413, 7, 90),
(414, 8, 90),
(415, 9, 90),
(416, 1, 91),
(417, 2, 91),
(418, 3, 91),
(419, 5, 91),
(420, 9, 91),
(421, 1, 92),
(422, 2, 92),
(423, 5, 92),
(424, 7, 92),
(425, 9, 92),
(426, 4, 93),
(427, 5, 93),
(428, 6, 93),
(429, 7, 93),
(430, 8, 93),
(431, 5, 94),
(432, 6, 94),
(433, 7, 94),
(434, 8, 94),
(435, 10, 94),
(436, 4, 95),
(437, 5, 95),
(438, 8, 95),
(439, 9, 95),
(440, 10, 95),
(441, 1, 96),
(442, 2, 96),
(443, 5, 96),
(444, 6, 96),
(445, 8, 96),
(446, 1, 97),
(447, 2, 97),
(448, 3, 97),
(449, 4, 97),
(450, 7, 97),
(451, 1, 98),
(452, 2, 98),
(453, 6, 98),
(454, 8, 98),
(455, 10, 98),
(456, 4, 99),
(457, 5, 99),
(458, 7, 99),
(459, 8, 99),
(460, 9, 99),
(461, 4, 100),
(462, 5, 100),
(463, 8, 100),
(464, 9, 100),
(465, 10, 100),
(466, 1, 101),
(467, 3, 101),
(468, 7, 101),
(469, 8, 101),
(470, 9, 101),
(471, 1, 102),
(472, 4, 102),
(473, 6, 102),
(474, 7, 102),
(475, 9, 102),
(476, 1, 103),
(477, 2, 103),
(478, 4, 103),
(479, 7, 103),
(480, 10, 103),
(481, 1, 104),
(482, 2, 104),
(483, 3, 104),
(484, 6, 104),
(485, 10, 104),
(486, 3, 105),
(487, 4, 105),
(488, 5, 105),
(489, 6, 105),
(490, 9, 105),
(491, 1, 106),
(492, 4, 106),
(493, 8, 106),
(494, 9, 106),
(495, 10, 106),
(496, 1, 107),
(497, 2, 107),
(498, 7, 107),
(499, 9, 107),
(500, 10, 107),
(501, 1, 108),
(502, 2, 108),
(503, 4, 108),
(504, 7, 108),
(505, 10, 108),
(506, 2, 109),
(507, 4, 109),
(508, 7, 109),
(509, 9, 109),
(510, 10, 109),
(511, 1, 110),
(512, 2, 110),
(513, 4, 110),
(514, 5, 110),
(515, 10, 110),
(516, 2, 111),
(517, 5, 111),
(518, 6, 111),
(519, 8, 111),
(520, 9, 111),
(521, 2, 112),
(522, 4, 112),
(523, 6, 112),
(524, 7, 112),
(525, 10, 112),
(526, 3, 113),
(527, 4, 113),
(528, 7, 113),
(529, 8, 113),
(530, 9, 113),
(531, 1, 114),
(532, 2, 114),
(533, 3, 114),
(534, 4, 114),
(535, 8, 114),
(536, 2, 115),
(537, 3, 115),
(538, 6, 115),
(539, 7, 115),
(540, 8, 115),
(541, 1, 116),
(542, 3, 116),
(543, 5, 116),
(544, 6, 116),
(545, 8, 116),
(546, 2, 117),
(547, 4, 117),
(548, 5, 117),
(549, 6, 117),
(550, 7, 117),
(551, 2, 118),
(552, 4, 118),
(553, 6, 118),
(554, 7, 118),
(555, 10, 118),
(556, 2, 119),
(557, 3, 119),
(558, 4, 119),
(559, 6, 119),
(560, 9, 119),
(561, 1, 120),
(562, 3, 120),
(563, 4, 120),
(564, 5, 120),
(565, 7, 120),
(566, 1, 121),
(567, 5, 121),
(568, 8, 121),
(569, 9, 121),
(570, 10, 121),
(571, 2, 122),
(572, 7, 122),
(573, 8, 122),
(574, 9, 122),
(575, 10, 122),
(576, 3, 123),
(577, 6, 123),
(578, 7, 123),
(579, 8, 123),
(580, 9, 123),
(581, 3, 124),
(582, 4, 124),
(583, 5, 124),
(584, 6, 124),
(585, 9, 124),
(586, 1, 125),
(587, 2, 125),
(588, 8, 125),
(589, 9, 125),
(590, 10, 125),
(591, 1, 126),
(592, 2, 126),
(593, 3, 126),
(594, 6, 126),
(595, 9, 126),
(596, 1, 127),
(597, 2, 127),
(598, 5, 127),
(599, 6, 127),
(600, 8, 127),
(601, 1, 128),
(602, 3, 128),
(603, 5, 128),
(604, 7, 128),
(605, 9, 128),
(606, 3, 129),
(607, 4, 129),
(608, 5, 129),
(609, 7, 129),
(610, 8, 129),
(611, 1, 130),
(612, 2, 130),
(613, 3, 130),
(614, 4, 130),
(615, 9, 130),
(616, 2, 131),
(617, 4, 131),
(618, 8, 131),
(619, 9, 131),
(620, 10, 131),
(621, 2, 132),
(622, 3, 132),
(623, 5, 132),
(624, 7, 132),
(625, 10, 132),
(626, 2, 134),
(627, 3, 134),
(628, 5, 134),
(629, 7, 134),
(630, 10, 134),
(631, 4, 135),
(632, 5, 135),
(633, 6, 135),
(634, 7, 135),
(635, 8, 135);

-- --------------------------------------------------------

--
-- 表的结构 `type`
--

CREATE TABLE `type` (
  `id` int NOT NULL,
  `name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL,
  `note` varchar(1024) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE `user` (
  `id` int NOT NULL,
  `email` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(15) COLLATE utf8mb4_general_ci NOT NULL,
  `pass` varchar(15) COLLATE utf8mb4_general_ci NOT NULL,
  `note` varchar(1024) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone` varchar(15) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`id`, `email`, `name`, `pass`, `note`, `phone`) VALUES
(1, '1260773726@qq.com', 'wmy', '123', NULL, '110'),
(2, 'test', 'test', 'test', NULL, '120'),
(3, 'sdust@sdust.edu.cn', 'sdust', '1234', NULL, '119'),
(24, 'tesxt', 'aaa', '123', '', '12'),
(38, '123123', '123123', '123123', '', '123123'),
(46, '12345', '', '', '', '132123'),
(47, '12sada345', '', '', '', ''),
(50, '123@1.1', 'wtq', '123', NULL, '10086'),
(51, 'jsjdjdhhdhdj', 'asdf', '123', NULL, '10001'),
(52, '1', '1', '1', NULL, '1'),
(54, 'sdyst@qq.com', '包子', '123', NULL, '17669456331'),
(55, '123456', 'Wtq', '123456', NULL, '15163848939'),
(58, '', '', '', '', '4354'),
(59, '15163848999@163.com', 'Wtq', '15243789197675', NULL, '15163848999'),
(61, '1692936422@qq.com', '李', '123456', NULL, '18336595214');

-- --------------------------------------------------------

--
-- 表的结构 `userpaper`
--

CREATE TABLE `userpaper` (
  `user_id` int NOT NULL,
  `paper_id` int NOT NULL,
  `last_time` int DEFAULT NULL,
  `score` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 表的结构 `userplan`
--

CREATE TABLE `userplan` (
  `user_id` int NOT NULL,
  `plan_id` int NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `last_date` int DEFAULT NULL,
  `submit_content` varchar(4096) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `note` varchar(1024) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 转存表中的数据 `userplan`
--

INSERT INTO `userplan` (`user_id`, `plan_id`, `start_date`, `end_date`, `last_date`, `submit_content`, `note`) VALUES
(1, 1, '2020-12-29', '2021-01-09', 1000, '123456', 'userPlanNote'),
(1, 2, '2020-12-23', '2021-01-05', 1500, 'wtqdsb', NULL),
(1, 3, '2020-12-08', '2021-01-03', 1300, 'wtqdsb', NULL),
(1, 4, '2020-12-13', '2021-01-11', 900, 'wtqdsb', NULL),
(1, 5, '2020-12-27', '2021-01-13', 1100, 'wtqdsb', NULL),
(1, 6, '2020-12-13', '2021-01-19', 800, 'wtqdsb', NULL),
(1, 7, '2020-12-16', '2021-01-08', 1700, 'wtqdsb', NULL),
(1, 8, '2020-12-23', '2021-01-03', 1200, 'wtqdsb', NULL),
(1, 9, '2020-12-09', '2021-01-20', NULL, 'wmydsb', NULL);

--
-- 转储表的索引
--

--
-- 表的索引 `discuss`
--
ALTER TABLE `discuss`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Discuss_id_uindex` (`id`),
  ADD KEY `discuss_user__fk` (`author`);

--
-- 表的索引 `paper`
--
ALTER TABLE `paper`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `paper_id_uindex` (`id`);

--
-- 表的索引 `plan`
--
ALTER TABLE `plan`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Plan_Id_uindex` (`id`);

--
-- 表的索引 `problem`
--
ALTER TABLE `problem`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `problem_id_uindex` (`id`);

--
-- 表的索引 `reply`
--
ALTER TABLE `reply`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Reply_id_uindex` (`id`),
  ADD KEY `reply_user__fk` (`author`),
  ADD KEY `reply_discuss_id_fk` (`discuss_id`);

--
-- 表的索引 `roomproblem`
--
ALTER TABLE `roomproblem`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `type`
--
ALTER TABLE `type`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Type_id_uindex` (`id`);

--
-- 表的索引 `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `User_Email_uindex` (`email`),
  ADD UNIQUE KEY `User_Id_uindex` (`id`),
  ADD UNIQUE KEY `user_phone_uindex` (`phone`);

--
-- 表的索引 `userpaper`
--
ALTER TABLE `userpaper`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `userpaper_paper_id_fk` (`paper_id`);

--
-- 表的索引 `userplan`
--
ALTER TABLE `userplan`
  ADD PRIMARY KEY (`user_id`,`plan_id`),
  ADD UNIQUE KEY `UserPlan_PlanId_uindex` (`plan_id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `discuss`
--
ALTER TABLE `discuss`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- 使用表AUTO_INCREMENT `paper`
--
ALTER TABLE `paper`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=137;

--
-- 使用表AUTO_INCREMENT `plan`
--
ALTER TABLE `plan`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- 使用表AUTO_INCREMENT `problem`
--
ALTER TABLE `problem`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- 使用表AUTO_INCREMENT `reply`
--
ALTER TABLE `reply`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- 使用表AUTO_INCREMENT `roomproblem`
--
ALTER TABLE `roomproblem`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=636;

--
-- 使用表AUTO_INCREMENT `type`
--
ALTER TABLE `type`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;

--
-- 限制导出的表
--

--
-- 限制表 `discuss`
--
ALTER TABLE `discuss`
  ADD CONSTRAINT `discuss_user__fk` FOREIGN KEY (`author`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- 限制表 `reply`
--
ALTER TABLE `reply`
  ADD CONSTRAINT `reply_discuss_id_fk` FOREIGN KEY (`discuss_id`) REFERENCES `discuss` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `reply_user__fk` FOREIGN KEY (`author`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- 限制表 `userpaper`
--
ALTER TABLE `userpaper`
  ADD CONSTRAINT `userpaper_paper_id_fk` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `userpaper_user__fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- 限制表 `userplan`
--
ALTER TABLE `userplan`
  ADD CONSTRAINT `userplan_plan_id_fk` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `userplan_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
