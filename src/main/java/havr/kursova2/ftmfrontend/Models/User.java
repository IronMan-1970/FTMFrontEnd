package havr.kursova2.ftmfrontend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
public class User {
        @JsonIgnore
        private ObjectId  id;
        private String name;
        private String psswd;

        public User(ObjectId id, String name, String psswd, String birthDay, long age, String country) {
                this.id = id;
                this.name = name;
                this.psswd = psswd;
                this.birthDay = birthDay;
                this.age = age;
                this.country = country;
        }

        private String birthDay;
        private long age;

        public User() {
        }

        private String country;

        public ObjectId getId() {
                return id;
        }

        public User setId(ObjectId id) {
                this.id = id;
                return this;
        }

        public String getName() {
                return name;
        }

        public User setName(String name) {
                this.name = name;
                return this;
        }

        public String getPsswd() {
                return psswd;
        }

        public User setPsswd(String psswd) {
                this.psswd = psswd;
                return this;
        }

        public String getBirthDay() {
                return birthDay;
        }

        public User setBirthDay(String birthDay) {
                this.birthDay = birthDay;
                return this;
        }

        public long getAge() {
                return age;
        }

        public User setAge(long age) {
                this.age = age;
                return this;
        }

        public String getCountry() {
                return country;
        }

        public User setCountry(String country) {
                this.country = country;
                return this;
        }

        public User(String name, String psswd, String birthDay, long age, String country) {

                this.name = name;
                this.psswd = psswd;
                this.birthDay = birthDay;
                this.age = age;
                this.country = country;
        }
}
