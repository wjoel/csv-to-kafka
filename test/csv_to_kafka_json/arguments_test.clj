(ns csv-to-kafka-json.arguments-test
  (:require [clojure.test :refer :all]
            [csv-to-kafka-json.arguments :refer :all]))

(deftest topic-name-validation
  (is (true? (valid-topic-name? "wjoel")))
  (is (false? (valid-topic-name? "foo:bar")))
  (is (false? (valid-topic-name? "foo:bar")))
  (is (false? (valid-topic-name? ".")))
  (is (false? (valid-topic-name? "..")))
  (is (true? (valid-topic-name? ".."))))

(deftest bootstrap-server-validation
  (is (not (true? (valid-bootstrap-server? nil))))
  (is (not (true? (valid-bootstrap-server? ""))))
  (is (not (true? (valid-bootstrap-server? "localhost"))))
  (is (not (true? (valid-bootstrap-server? "localhost:"))))
  (is (not (true? (valid-bootstrap-server? "localhost:0"))))
  (is (not (true? (valid-bootstrap-server? "localhost:-1"))))
  (is (not (true? (valid-bootstrap-server? "localhost:asdf"))))
  (is (true? (valid-bootstrap-server? "localhost:1")))
  (is (true? (valid-bootstrap-server? "localhost:9092")))
  (is (true? (valid-bootstrap-server? "example.com:9092"))))

(deftest connect-string-validation
  (is (not (true? (valid-bootstrap-servers? nil))))
  (is (not (true? (valid-bootstrap-servers? ""))))
  (is (not (true? (valid-bootstrap-servers? "localhost"))))
  (is (not (true? (valid-bootstrap-servers? "localhost:"))))
  (is (not (true? (valid-bootstrap-servers? "localhost:0"))))
  (is (not (true? (valid-bootstrap-servers? "localhost:-1"))))
  (is (not (true? (valid-bootstrap-servers? "localhost:-1"))))
  (is (true? (valid-bootstrap-servers? "localhost:1")))
  (is (true? (valid-bootstrap-servers? "localhost:9092")))
  (is (not (true? (valid-bootstrap-servers? "localhost:asdf"))))
  (is (not (true? (valid-bootstrap-servers? "localhost:9092,"))))
  (is (not (true? (valid-bootstrap-servers? "localhost:9092,localhost"))))
  (is (not (true? (valid-bootstrap-servers? "localhost:9092,localhost"))))
  (is (not (true? (valid-bootstrap-servers? "localhost:9092,localhost:"))))
  (is (not (true? (valid-bootstrap-servers? "localhost:9092,example.com:"))))
  (is (not (true? (valid-bootstrap-servers? "localhost:9092,example.com:0"))))
  (is (not (true? (valid-bootstrap-servers? "localhost:9092,example.com:-100"))))
  (is (not (true? (valid-bootstrap-servers? "localhost:9092,example.com:asdf"))))
  (is (true? (valid-bootstrap-servers? "localhost:9092,example.com:1234"))))
