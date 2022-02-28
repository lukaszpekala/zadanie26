insert into match (id, host, visitor, bet_count)
values
       (1, 'Koluszki', 'Młociny', 3),
       (2, 'Grabów', 'Grabowo', 3),
       (3, 'Brezo', 'Nanowice', 1),
       (4, 'Kramków', 'Torminów', 1);


insert into bet (id, host_score, visitor_score, match_id, bid, name, promo)
values
    (1, 4, 3, 1, 4, 'Adam', false),
    (2, 0, 0, 1, 12, 'Marian', false),
    (3, 2, 1, 1, 6, 'Anna', false),
    (4, 1, 0, 2, 3, 'Kasia', false),
    (5, 0, 6, 2, 7, 'Asia', false),
    (6, 2, 2, 4, 78, 'Basia', false),
    (7, 4, 3, 3, 88, 'Monika', false);


insert into match (host, visitor, host_score, visitor_score, bet_count)
values
    ('Kamień', 'Płomyk', 2, 2, 7),
    ('Mops', 'Cmyk', 3, 2, 6),
    ('Retina', 'Ips', 1, 1, 6),
    ('Alfa', 'Romeo', 4, 2, 5),
    ('Smyk', 'Bryk', 0, 0, 5);


insert into bet (host_score, visitor_score, match_id, bid, name, win, promo)
values
    (4, 3, 5, 4, 'Adam', 0, false),
    (0, 0, 5, 12, 'Marian', 0, false),
    (2, 2, 5, 6, 'Anna', 48, true),
    (1, 0, 5, 3, 'Kasia', 0, false),
    (0, 6, 5, 7, 'Asia', 0, false),
    (2, 2, 5, 8, 'Basia', 48, false),
    (4, 3, 5, 88, 'Monika', 0, false);

insert into bet (host_score, visitor_score, match_id, bid, name, win, promo)
values
    (4, 3, 6, 4, 'Adam', 0, false),
    (3, 2, 6, 7, 'Marian', 49, false),
    (2, 2, 6, 6, 'Anna', 0, false),
    (1, 0, 6, 3, 'Kasia', 0, false),
    (0, 6, 6, 7, 'Asia', 0, false),
    (2, 2, 6, 8, 'Basia', 0, false);

insert into bet (host_score, visitor_score, match_id, bid, name, win, promo)
values
    (4, 3, 7, 4, 'Adam', 0, false),
    (3, 2, 7, 7, 'Marian', 0, false),
    (2, 2, 7, 6, 'Anna', 0, false),
    (1, 1, 7, 3, 'Kasia', 18, true),
    (0, 6, 7, 7, 'Asia', 0, false),
    (2, 2, 7, 8, 'Basia', 0, false);

insert into bet (host_score, visitor_score, match_id, bid, name, win, promo)
values
    (4, 2, 8, 4, 'Adam', 32, false),
    (3, 2, 8, 7, 'Marian', 0, false),
    (4, 2, 8, 6, 'Anna', 48, false),
    (1, 1, 8, 3, 'Kasia', 0, false),
    (0, 6, 8, 7, 'Asia', 0, false);

insert into bet (host_score, visitor_score, match_id, bid, name, win, promo)
values
    (4, 2, 9, 4, 'Adam', 0, false),
    (3, 2, 9, 7, 'Marian', 0, false),
    (4, 2, 9, 6, 'Anna', 0, false),
    (1, 1, 9, 3, 'Kasia', 0, false),
    (0, 0, 9, 7, 'Asia', 14, false);

INSERT INTO user_role(role)
VALUES ('ROLE_USER'), ('ROLE_ADMIN');

insert into user (username, password, first_name, last_name)
values
('marian', '{noop}osom', 'Marian', 'Szczęch'),
('adam', '{noop}osom', 'Adam', 'Gąska'),
('zenon', '{noop}osom', 'Zenon', 'Bąk');

insert into user_roles(user_id, roles_id)
values
(1, 1), (1, 2),
(2, 1), (2, 2),
(3, 1);


