SELECT course.id, course.pd, course.xf FROM `less` LEFT JOIN `less` ON less.course = course.id WHERE less.student != 'op' AND course.pd = 0 OR `course`.`id` IS NULL;

#SELECT course.id, course.pd, course.xf FROM `less`RIGHT JOIN course ON course.id = less.course WHERE less.student != 'op' AND course.pd = 0;

SELECT c.id, c.`xf` FROM course c WHERE c.pd = 0 AND c.id NOT IN (SELECT l.course FROM `less` l WHERE l.student = 'NAME');

SELECT l.course, c.`xf` FROM LESS l JOIN course c ON l.course = c.id WHERE l.student = 'csmht' AND c.pd = 0;