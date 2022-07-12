-- TRUNCATE todo;

-- ALTER SEQUENCE todo_todo_id_seq RESTART WITH 1;

SELECT * FROM pg_class c WHERE c.relkind = 'S';
