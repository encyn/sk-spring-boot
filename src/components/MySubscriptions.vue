<template>
    <Navbar>
        <div class="mx-auto" style="width: 80%">
            <h2>My subscriptions</h2>
            <table class="table table-striped table-hover table-bordered text-center ">
                <thead>
                </thead>
                <tbody>
                <tr v-for="i in items" v-bind:key="i">
                    <td>
                        <label class="form-checkbox">
                            <input type="checkbox" :value="{name:i}" v-model="selected">
                            <i class="form-icon"></i>
                        </label>
                    </td>
                    <td>{{i}}</td>
                </tr>
                </tbody>
            </table>
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
            selected: null,
            selectAll: false,
            request: null
        }),
        methods: {
            unsubscribe: function(){
                if (!this.selectAll) {
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
                }
            },
            select:function() {
                this.selected = null;
                if (!this.selectAll) {
                    for (let i in this.items) {
                        this.selected = this.items[i];
                    }
                }
            },
            init: function(){
                axios.post('http://localhost:9000/service1/sub/findForEmail', this.$store.state.user.email).then((response) => {
                    this.items = response.data.list;
                }).catch((error) => {
                    this.$notify({
                        group: 'Notifications',
                        type: 'vue-notification error',
                        title: 'Initialise exception',
                        text: error
                    });
                });
            }
        }
    }
</script>
<style>
    h2{
        padding-top: 1%;
        padding-bottom: 1%
    }
</style>