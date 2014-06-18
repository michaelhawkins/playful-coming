-- Add ability to collect zip code

# --- !Ups
ALTER TABLE person ADD COLUMN zipcode VARCHAR(5)


# --- !Downs
ALTER TABLE person DROP COLUMN IF EXISTS zipcode