<template>
    <Navbar>
        <div class="mx-auto" style="width: 80%">
            <h2>Available lists</h2>
            <table class="table table-striped table-hover table-bordered text-center ">
                <tbody>
                <tr v-for="i in items" v-bind:key="i.id">
                    <td v-if="$store.state.loggedIn===true">
                        <label class="form-checkbox">
                            <input type="checkbox" :value="{name:i}" v-model="selected">
                            <i class="form-icon"></i>
                        </label>
                    </td>
                    <td>{{i}}</td>
                </tr>
                </tbody>
            </table>
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
        data: () => ({
            items: [],
            selected: "",
            selectAll: false,
            request: null
        }),
        methods: {
            subscribe: function(){
                if (!this.selectAll) {
                    axios.post('http://localhost:9000/service1/sub/save', {email:this.$store.state.user.email, list:this.selected}).then((response) => {
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
        },
    }
</script>
<style>
    h2{
        padding-top: 1%;
        padding-bottom: 1%
    }
</style>