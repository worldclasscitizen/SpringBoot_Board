INSERT INTO article(title, content) VALUES('1번 게시물의 제목입니다.', '1번 게시물입니다.');
INSERT INTO article(title, content) VALUES('2번 게시물의 제목입니다.', '2번 게시물입니다.');
INSERT INTO article(title, content) VALUES('3번 게시물의 제목입니다.', '3번 게시물입니다.');

INSERT INTO comment(article_id, nickname, body) VALUES(1, '최진우', '안녕하세요. 댓글입니다.');
INSERT INTO comment(article_id, nickname, body) VALUES(1, '가짜 최진우', '네. 저는 댓글 아닙니다.');