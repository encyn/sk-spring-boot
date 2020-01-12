<template>
    <Navbar>
        <div class="mx-auto" style="width: 80%">
            <h2>Available lists</h2>
            <ul>
                <li v-for="i in items" v-bind:key="i">
                    <label v-if="$store.state.loggedIn===true">
                        <input type="radio" :value=i v-model="selected">
                    </label>
                    {{i}}
                </li>
            </ul>
            <button v-if="$store.state.loggedIn===true" class="btn btn-primary" @click="subscribe">Subscribe</button>
        </div>
    </Navbar>
</template>
<script>
    import Navbar from "./Navbar";
    import axios from 'axios';
    axios.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    export default {
        name: "HomePage",
        components: {
            Navbar
        },
        data: function () {
            return {
                items: [],
                selected: null
            }
        },
        methods: {
            subscribe: function(){
                axios.post('http://localhost:9000/service1/sub/save',
                    {email:this.$store.state.user.email, list:this.selected, frequency:"DAILY"}).then((response) => {
                    this.asd = response;
                    this.$router.push('/mySubscriptions');
                    this.$notify({
                        group: 'Notifications',
                        type: 'vue-notification success',
                        position: 'top left',
                        title: 'Success',
                        text: 'Subscription successful.'
                    });
                }).catch((error) => {
                    this.$notify({
                        group: 'Notifications',
                        type: 'vue-notification error',
                        title: 'Initialise exception',
                        text: error
                    });
                });
        },
            init: function(){
                axios.get('http://localhost:9000/service2/coupon/findLists').then((response) => {
                    this.items = response.data;
                }).catch((error) => {
                    this.$notify({
                        group: 'Notifications',
                        type: 'vue-notification error',
                        title: 'Initialise exception',
                        text: error
                    });
                });
            }
        },
        beforeMount(){
            this.init()
        }
    }
</script>
<style>
    h2{
        padding-top: 1%;
        padding-bottom: 1%
    }
</style>