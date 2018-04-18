START TRANSACTION;

DELETE FROM schedule_mt WHERE schedule_id >= 1;
DELETE FROM grade WHERE student_id >= 1;

UPDATE or_no_mt SET is_used = 0 WHERE or_no_id >= 1;
DELETE FROM transaction_balancebreakdown WHERE balancebreakdown_id >= 1;
DELETE FROM transaction_penalty WHERE transaction_id >= 1;
DELETE FROM penalty WHERE penalty_id >= 1;
DELETE FROM transaction_mt WHERE student_id >= 1;
DELETE FROM balancebreakdown WHERE student_id >= 1;
DELETE FROM student_discount WHERE student_id >= 1;
DELETE FROM credentials_received_lt WHERE registration_id >= 1;
DELETE FROM penalty WHERE penalty_id >= 1;
DELETE FROM student_discount WHERE discount_id >= 1;

DELETE FROM enrollment WHERE student_id >= 1;
DELETE FROM schoolyear_student_lt WHERE schoolyear_student_lt_id >=1;
DELETE FROM section_student WHERE student_id >= 1;
DELETE FROM promotion WHERE promotion_id >= 1;
DELETE FROM summer WHERE summer_id >= 1;
DELETE FROM student_mt WHERE student_id >= 1;
DELETE FROM admission_mt WHERE registration_id >= 1;

COMMIT;