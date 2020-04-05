import React from 'react';
import {Datagrid, EditButton, Filter, List, TextField, TextInput} from 'react-admin';

const Filters = (props) => (
    <Filter {...props}>
        <TextInput label={'名称'} source="name" allowEmpty alwaysOn/>
    </Filter>
);

export const VocabularyList = props => {
    console.info('VocabularyList:', props);
    return (
        <List {...props} title={`${props.options.label}列表`} filters={<Filters/>}
              sort={{field: 'id', order: 'desc'}}>
            <Datagrid rowClick="show">
                <TextField label={'名称'} source="name"/>
                <TextField label={'解释'} source="explanation"/>
                <EditButton/>
            </Datagrid>
        </List>
    )
};