<template>
    <Navbar>
        <div class="mx-auto" style="width: 80%">
            <h2>My subscriptions</h2>
            <ul>
                <li v-for="i in items" v-bind:key="i">
                    <input type="radio" :value=i v-model="selected">
                    {{i}}
                </li>
            </ul>
            <button class="btn btn-primary" @click="unsubscribe">Unsubscribe</button>
        </div>
    </Navbar>
</template>
<script>
    import Navbar from "./Navbar";
    import axios from 'axios';
    axios.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    export default {
        name: "MySubscriptions",
        components: {
            Navbar
        },
        data: () => ({
            items: [],
            selected: null
        }),
        methods: {
            unsubscribe: function(){
                axios.post('http://localhost:9000/service1/sub/delete', {email:this.$store.state.user.email, list:this.selected}).then((response) => {
                    // eslint-disable-next-line no-console
                    this.asd = response;
                    this.$notify({
                        group: 'Notifications',
                        type: 'vue-notification success',
                        position: 'top left',
                        title: 'Success',
                        text: 'You unsubscribed successfuly.'
                    });
                    this.$router.push('/');
                }).catch((error) => {
                    this.$notify({
                        group: 'Notifications',
                        type: 'vue-notification error',
                        title: 'Unsub exception',
                        text: error
                    });
                });
            },
            init: function(){
                axios.post('http://localhost:9000/service1/sub/findForEmail', {email: this.$store.state.user.email}).then((response) => {
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