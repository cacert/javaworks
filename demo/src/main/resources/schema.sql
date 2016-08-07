create table IF NOT EXISTS octopus_properties (
 oid identity,
 key varChar(50) not null,
 value varChar(100) not null,
 drcvalue varChar(100))
 ;