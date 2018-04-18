

SELECT * FROM student_mt WHERE student_no = 20180036;

UPDATE student_mt SET isActive = 0 WHERE student_id = 164;
DELETE FROM transaction_balancebreakdown WHERE balancebreakdown_id >= (SELECT MIN(balancebreakdown_id) FROM balancebreakdown WHERE student_id = 164) AND balancebreakdown_id <=(SELECT MAX(balancebreakdown_id) FROM balancebreakdown WHERE student_id = 164); 
DELETE FROM balancebreakdown WHERE student_id = 164;
DELETE FROM transaction_mt WHERE student_id = 164;
DELETE FROM enrollment WHERE student_id = 164;
DELETE FROM schoolyear_student_lt WHERE student_id = 164;
DELETE FROM student_mt WHERE student_id = 164;