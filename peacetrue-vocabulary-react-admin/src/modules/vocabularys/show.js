import React from 'react';
import {Show, SimpleShowLayout, TextField} from 'react-admin';

export const VocabularyShow = (props) => {
    console.info('VocabularyShow:', props);
    return (
        <Show {...props} title={`${props.options.label}#${props.id}`}>
            <SimpleShowLayout>
                <TextField label={'名称'} source="name"/>
                <TextField label={'解释'} source="explanation"/>
            </SimpleShowLayout>
        </Show>
    );
};
